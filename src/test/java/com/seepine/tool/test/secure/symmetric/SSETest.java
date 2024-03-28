package com.seepine.tool.test.secure.symmetric;

import com.seepine.tool.secure.symmetric.SSE;
import com.seepine.tool.time.StopWatch;

public class SSETest {
  public static void main(String[] args) {

    SSE sse = new SSE("bvuh4xrfogqxmedc");
    String data =
        "今天天气真不错，今天天气真不错，今天天气真不错，今天天气真不错，今天天气真不错，今天天气真不错，今天天气真不错，今天天气真不错，今天天气真不错，今天天气真不错，今天天气真不错";
    StopWatch watch = StopWatch.quickStart();
    String cipher = sse.encode(data);
    watch.stop();
    System.out.println(cipher);
    System.out.println("原文长度：" + data.length());
    System.out.println("加密长度：" + cipher.length());
    System.out.println(watch.getMillis());

    System.out.println(sse.decode(cipher));

    System.out.println(cipher.contains(sse.encode("天气")));
    System.out.println(cipher.contains(sse.encode("天气2")));
  }
}
