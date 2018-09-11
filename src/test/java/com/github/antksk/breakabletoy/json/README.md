
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
2. @JacksonInject
3. @JsonAnySetter
4. @JsonSetter
5. @JsonDeserialize

## Jackson Property Inclusion Annotations
1. @JsonIgnoreProperties
2. @JsonIgnore
3. @JsonIgnoreType
4. @JsonInclude
5. @JsonAutoDetect

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