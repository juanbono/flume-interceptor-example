package org.nosql.flume.interceptor

import java.io.{BufferedWriter, File, FileWriter}
import java.nio.charset.StandardCharsets

import org.apache.flume.interceptor.Interceptor
import org.apache.flume.{Context, Event}

import scala.collection.JavaConverters._

class JsonInterceptor(ctx: Context) extends Interceptor {

  private var file: File = _
  private var bw: BufferedWriter = _

  def initialize(): Unit = {
    file = new File("./tweet_log.txt")
    bw = new BufferedWriter(new FileWriter(file))
  }

  def close(): Unit = {
    bw.close()
  }

  // handles a single tweet
  def intercept(event: Event): Event = {

    val body = new String(event.getBody, StandardCharsets.UTF_8)

    val data = ujson.read(body)
    val text = data("text").str

    val wordToFilter = ctx.getString("json_filter.word")

    if (text.toLowerCase().contains(wordToFilter))
      bw.append(text)

    event
  }

  // handles a list of tweets
  def intercept(list: java.util.List[Event]): java.util.List[Event] = {
    val events: Seq[Event] = list.asScala

    events.map(e => intercept(e)).asJava
  }
}
