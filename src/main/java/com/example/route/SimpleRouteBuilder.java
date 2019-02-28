package com.example.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.example.exception.CamelCustomException;
import com.example.processor.MyProcessor;

public class SimpleRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelCustomException.class).process(new Processor() {

            public void process(Exchange exchange) throws Exception {
                System.out.println("handling ex");
            }
        }).log("Received body ${body}").handled(true);

        from("file:F:\\data\\exception\\inbox?noop=true").process(new MyProcessor()).to("file:C:/outputFolder");

        from("file:F:\\data\\exception\\inbox2?noop=true").process(new MyProcessor()).to("file:C:/outputFolder");
    }

}
