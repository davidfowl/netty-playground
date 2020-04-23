package hello;

import java.util.concurrent.ScheduledExecutorService;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.*;

public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

	private ScheduledExecutorService service;

	public HelloServerInitializer(ScheduledExecutorService service) {
		this.service = service;
	}

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline()
				.addLast("encoder", new HttpResponseEncoder())
				// .addLast("logging1", new LoggingHandler(LogLevel.INFO))
				.addLast("decoder", new HttpRequestDecoder(4096, 8192, 8192, false))
				//.addLast("logging2", new LoggingHandler(LogLevel.INFO))
				.addLast("handler", new HelloServerHandler(service))
				//.addLast("logging3", new LoggingHandler(LogLevel.INFO))
				;
	}
}