package com.github.antksk.breakabletoy.util;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

public final class HttpServletRequestAnalysis {
    public final static String      HEADER_X_FORWARDED_FOR = "X-FORWARDED-FOR";
    public final static String      HEADER_REMOTE_ADDR     = "REMOTE-ADDR";
    public final static String      HEADER_UNKNOWN         = "UNKNOWN";
    public static final String      URI_TEMPLATE_VARIABLES_ATTRIBUTE = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;

    private final HttpServletRequest request;

    private HttpServletRequestAnalysis(HttpServletRequest request){
        this.request = request;
    }

    public static HttpServletRequestAnalysis of(HttpServletRequest request){
        return new HttpServletRequestAnalysis(request);
    }

    /**
     * @method : ip
     * @comments : 사용자 접속 IP 정보를 가져옴
     * @return
     */
    public Optional<String> optionalxForwardedFor(){
        return Optional
                .ofNullable(request.getHeader(HEADER_X_FORWARDED_FOR));
    }

    public String xForwardedFor(){
        return optionalxForwardedFor().orElse(HEADER_UNKNOWN);
    }

    public String remoteAddr(){
        return Optional
                .ofNullable(request.getHeader(HEADER_REMOTE_ADDR))
                .orElse(request.getRemoteAddr());
    }

    public String ip() {
        return optionalxForwardedFor()
                .orElse(remoteAddr());
    }

    @ToString
    @EqualsAndHashCode(exclude="value")
    public static final class HttpServletRequestAnalysisResource {
        private final String key;
        private final Optional<String> value;
        private final String defaultValue;

        private HttpServletRequestAnalysisResource(final String key, final String value, final String defaultValue ){
            this.key = key;
            this.value = Optional.ofNullable(value);
            this.defaultValue = defaultValue;
        }

        public static HttpServletRequestAnalysisResource of(String key, String value, String defaultValue){
            return new HttpServletRequestAnalysisResource(key, value, defaultValue);
        }

        public static HttpServletRequestAnalysisResource ofDefaultValueIsEmpty(String key, String value){
            return of(key, value, "");
        }

        public static String underscoreToCamelKey(String key){
            if(hasFirstCharIsUnderscore(key)){
                return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key.substring(1));
            }
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
        }

        private static boolean hasFirstCharIsUnderscore(String key) {
            return 0 == key.indexOf("_");
        }

        public static String camelToUnderscoreKey(String key){
            return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
        }

        public String getSmartParam(HttpServletRequest request, String paramName, String defaultValue ){
            final String underscoreToCamelParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, paramName);
            return Optional.ofNullable(request.getParameter(paramName))
                    .orElse(Optional.ofNullable(request.getParameter(underscoreToCamelParam))
                            .orElse(defaultValue));
        }

        public boolean hasRequestResource(){
            return this.value.isPresent();
        }
    }

    public Map<String, String> pathVariables() {
        return new ImmutableMap.Builder<String,String>()
                .putAll(springUriVariables())
                .build();
    }

    private Map<String, String> springUriVariables() {
        return (Map<String, String>) request.getAttribute(URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }

    public Map<String, String> parameters() {
        final ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<>();
        Iterators.forEnumeration(request.getParameterNames())
                .forEachRemaining(name -> builder.put(name, request.getParameter(name)));
        return builder.build();
    }

    public Map<String, String> headers() {
        final ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<>();
        Iterators.forEnumeration(request.getHeaderNames())
                .forEachRemaining(name -> builder.put(name, request.getHeader(name)));
        return builder.build();
    }
}
