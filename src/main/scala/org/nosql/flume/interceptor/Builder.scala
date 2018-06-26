package org.nosql.flume.interceptor

import org.apache.flume.Context
import org.apache.flume.interceptor.Interceptor


class Builder extends Interceptor.Builder {
  private var ctx: Context = _

  def configure(context: Context): Unit = ctx = context

  def build(): Interceptor = new JsonInterceptor(ctx)
}
