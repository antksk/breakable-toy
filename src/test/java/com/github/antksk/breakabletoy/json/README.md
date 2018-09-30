
## Jackson Serialization Annotations
1. @JsonAnyGetter : 파라미터 타입이 Map인 경우 key값을 기준으로 value값을 모두 가져와 JSON으로 만듬 
2. @JsonGetter : 지정된 이름으로 JSON property 생성
3. @JsonPropertyOrder : property의 순서를 지정함
4. @JsonRawValue : 문자열에 포함된 JSON을 jackson직렬화를 통해 바로 JSON으로 생성함
5. @JsonValue : enum 타입을 사용할 때 맴버 필드 중 지정된 필드를 Value로 사용함
6. @JsonRootName : JSON으로 생성되는 객체의 root 이름을 지정함
7. @JsonSerialize : 사용자 정의 serializer를 선언하여 연결 시킴

## Jackson Deserialization Annotations
1. @JsonCreator : 생성자나 factory를 통해 다음과 같이 정확히 일치 하지 않는 JSON에 대해 property 매칭이 될수 있도록 동작함
    ```json
     {
         "id":1,
         "theName":"My bean"
     }
    ```
    ```java
       @Getter
       public static final class TestJsonObject0 {
           private final int id;
           private final String name;
   
           @JsonCreator
           public TestJsonObject0(
                   @JsonProperty("id") int id,
                   @JsonProperty("theName") String name
           ) {
               this.id = id;
               this.name = name;
           }
        }
    ```
2. @JacksonInject : 지정된 JSON데이터가 아닌 곳에서 특정 값을 주입 받을수 있도록 동작함
    - __주의__ 아래와 같이 한 클래스에 여러 필드로 ```JacksonInject```를 사용하면,
    ```java.lang.IllegalArgumentException: No injectable id with value 'id2' found (for property 'id2')``` 발생함
    ```java
           public static final class TestJsonObject {
               @JacksonInject("id")
               private int id;
       
               @JacksonInject("id2")
               private int id2;
            }
    ```
    - JacksonInject에 아무 정보도 주지 않으면 Inject 대상 이름을 ```id```로 설정됨, 특정 이름을 주고 싶으면 다음과 같이 설정하면 됨
    ```java
       public static final class TestJsonObject {
           @JacksonInject("test")
           private int id;
       }
       
    ```
    ```java
       public class _02_JacksonInjectTest {
           @Test
           public void test() throws IOException {
            
            InjectableValues inject = new InjectableValues.Std()
            .addValue("test", 1);
         
            TestJsonObject bean = new ObjectMapper()
               .reader(inject)
               .forType(TestJsonObject.class)
               .readValue(json);
              
           }
       }
    ```

3. @JsonAnySetter : JSON property 정보가 명확하지 않아 Map의 유연성을 필요 할때 사용
4. @JsonSetter : JsonProperty의 대안으로 사용됨, setter 메서드에 지정하며, JSON property 이름과 매칭되지 않을때 사용함
5. @JsonDeserialize : 사용자 정의 deserializer를 연결 시킴

## Jackson Property Inclusion Annotations
1. @JsonIgnoreProperties : JSON 문자열로 생성될 때 클래스 레벨에서 특정 property를 무시(Ignore) 처리
2. @JsonIgnore : JSON 문자열로 생성될 때 필드, 생성자, 메서드 레벨에서 특정 property를 무시(Ignore) 처리
3. @JsonIgnoreType : JSON 문자열로 생성될 때 클래스(type) 레벨에서 모든 property를 무시(Ignore) 처리,
즉, vo개념을 가진 type으로 사용된 클래스가 존재하는 경우, 그 type 클래스를 JSON 문자열로 생성하지 않음
4. @JsonInclude : JSON 문자열로 생성될 때 지정된 Include 전략에 따라 필드를 제외(exclude)시킬지 여부 결정
추가적으로 value와 content라는 개념을 기반으로 Include 전략을 설정할 수 있는데, 예를 들어 ```List<String>```와 같이
컨텐츠 기반의 타입에 대해서 content는 ```List```를 의미하고, value는 그 content에 들어 있는 값을 의미한다.
    - Include.ALWAYS : property 값이 존재하지 않아도 null이나 공백, []등으로 채워 property를 표시함
    - Include.NON_NULL : property 값이 null이 아닌경우 표시, 단 Optional객체가 empty인 경우 null로 표시함
    - Include.NON_ABSENT : property 값이 null이거나 Optional객체가 empty인 경우 표시 하지 않음, 단 빈 배열이거나 공백 문자열인 경우엔 표시
    - Include.NON_EMPTY : property값이 빈 경우(null, Optional.empty, 빈 배열, 공백 문자) 표시하지 않음
    - Include.NON_DEFAULT : 보편적인 기본값(자바 원시타입 초기값, 공백 문자, 빈 배열)인 경우 표시하지 않음
    - Include.CUSTOM : ```@JsonInclude(content = JsonInclude.Include.CUSTOM, contentFilter = XXXXFilter.class)``` 와 같이 설정후
    ```XXXXFilter```클래스에 ```public boolean equals(Object obj)``` 메서드를 정의한다. 만약 ```equals```메서드가 false를 리턴해야 직렬화된다.
        - 직렬화 하려는 대상이 Collection 타입인 경우, content 키워드를 사용해야 함 
        ```
            @JsonInclude(content=JsonInclude.Include.CUSTOM, contentFilter = PhoneFilter.class)
            private Map<String,String> phones = new ImmutableMap.Builder<String,String>()
        ```
        - 직렬화 하려는 대상이 ValueObject인 경우, value 키워드를 사용해야함
        ```
             @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = DateOfBirthFilter.class)
             private Date dateOfBirth = Date.from(ZonedDateTime.now().plusDays(1).toInstant());
        ```
    - Include.USE_DEFAULTS : 필드 객체를 생성할 때 상위 수준의 기본값(higher-level defaults)을 설정, 만약 USE_DEFAULTS 값이 설정 되지 않으면 
    ```ObjectMapper```에 설정된 글로벌 직렬화 포함 규칙(global serialization inclusion rules )이 적용됨
5. @JsonAutoDetect : JSON 문자열로 생성될 때 클래스 레벨에서 접근 한정자(public, private, protected)에 따라 필드의 표시 여부를 결정
    - Visibility.ANY : 
    - Visibility.NON_PRIVATE : 
    - Visibility.PROTECTED_AND_PUBLIC : 
    - Visibility.PUBLIC_ONLY : 
    - Visibility.NONE : 
    - Visibility.DEFAULT : 

## Jackson General Annotations
1. @JsonProperty
2. @JsonFormat
3. @JsonUnwrapped
4. @JsonView
5. @JsonManagedReference, @JsonBackReference
6. @JsonIdentityInfo
7. @JsonFilter

- https://www.baeldung.com/jackson-annotations
- https://cheese10yun.github.io/jackson-annotation/
- http://tutorials.jenkov.com/java-json/jackson-annotations.html
- JsonInclude.Include.CUSTOM
    - https://www.logicbig.com/tutorials/misc/jackson/json-include-customized.html
    - https://www.logicbig.com/tutorials/misc/jackson/json-include-with-content-attribute.html
- JsonInclude.Include.USE_DEFAULTS
    - https://www.logicbig.com/tutorials/misc/jackson/json-include-use-defaults.html
    - http://fasterxml.github.io/jackson-annotations/javadoc/2.7/com/fasterxml/jackson/annotation/JsonInclude.Include.html#USE_DEFAULTS