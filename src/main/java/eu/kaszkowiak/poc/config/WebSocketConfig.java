package eu.kaszkowiak.poc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

import java.security.Principal;
import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends WebSocketMessageBrokerConfigurationSupport implements WebSocketMessageBrokerConfigurer {

        private final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

        @Override
        public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
            super.configureWebSocketTransport(registry);
        }

        @Override
        public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
            return super.configureMessageConverters(messageConverters);
        }

        @Override
        public void configureClientInboundChannel(ChannelRegistration registration) {
            registration.setInterceptors(new ChannelInterceptorAdapter() {

                @Override
                public Message<?> preSend(Message<?> message, MessageChannel channel) {

                    StompHeaderAccessor accessor =
                            MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                    if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                        Principal user = () -> message.getHeaders().get("simpSessionId", String.class);
                        accessor.setUser(user);
                    }

                    return message;
                }
            });
        }

        @Override
        public void configureClientOutboundChannel(ChannelRegistration registration) {
            super.configureClientOutboundChannel(registration);
        }


        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            super.addArgumentResolvers(argumentResolvers);
        }

        @Override
        public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
            super.addReturnValueHandlers(returnValueHandlers);
        }

        @Override
        public void configureMessageBroker(MessageBrokerRegistry config) {
            config.enableSimpleBroker("/topic", "/queue");
            config.setApplicationDestinationPrefixes("/app");
        }

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint("/get/allFiles", "/get/allFiles")
                    .setAllowedOrigins("http://localhost:4200")
                    .withSockJS();
        }

        @Bean
        public WebSocketHandler subProtocolWebSocketHandler() {
            return new CustomSubProtocolWebSocketHandler(clientInboundChannel(), clientOutboundChannel());
        }


}