package com.github.antksk.breakabletoy.csv;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvTest
{

//
//    @Setter
//    @ToString
//    static class ServiceData{
//        private int row;
//        private String program;
//        private String thema;
//
//        @Getter
//        private String regin;
//        private String programDescrption;
//        private String programDescrption2;
//    }
//
//    public List<ServiceData> loadManyToManyRelationship(String fileName) {
//        try {
//            CsvMapper mapper = new CsvMapper();
//            // CsvSchema csvSchema = CsvSchema.emptySchema().withSkipFirstDataRow(true);
//
//            CsvSchema csvSchema = CsvSchema.builder()
//                                            .addColumn("row", CsvSchema.ColumnType.NUMBER)
//                                            .addColumn("program", CsvSchema.ColumnType.STRING)
//                                            .addColumn("thema", CsvSchema.ColumnType.STRING)
//                                            .addColumn("regin", CsvSchema.ColumnType.STRING)
//                                            .addColumn("programDescrption", CsvSchema.ColumnType.STRING)
//                                            .addColumn("programDescrption2", CsvSchema.ColumnType.STRING)
//
//                .build()
//                                           // .withoutColumns()
//                                           .withSkipFirstDataRow(true);
//
//            // mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
//            //File file = new ClassPathResource(fileName).getFile();
//            File file = Paths.get(fileName).toFile();
//            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "EUC-KR");
//            MappingIterator<ServiceData> readValues = mapper.readerFor(ServiceData.class).with(csvSchema).readValues(inputStreamReader);
//            return readValues.readAll();
//        } catch (Exception e) {
//            log.error("Error occurred while loading many to many relationship from file = " + fileName, e);
//            return Collections.emptyList();
//        }
//    }
//
//    @Test
//    public void csv_test(){
//        final String fileName = "/Users/a1101049/Downloads/서버개발_사전과제2_2017년_국립공원_생태관광_정보.csv";
//
//        // log.debug("{} {}", Paths.get(fileName).toFile().exists(), Files.exists(Paths.get(fileName)));
//        List<ServiceData> arg = loadManyToManyRelationship(fileName);
//        arg.forEach(e->log.debug("{}", e.getRegin()));
//    }
//
//
//    @Test
//    public void komoran_test(){
//        final Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
//        final String sentence = "강원도 속초,\"설악산 탐방안내소, 신흥사, 권금성, 비룡폭포\",\" 설악산은 왜 설악산이고, 신흥사는 왜 신흥사일까요? 설악산에 대해 정확히 알고, 배우고, 느낄 수 있는 당일형 생태관광입니다" +
//            ".\"";
//        final Set<String> poss = Sets.newHashSet("VV", "NNP");
//
//        //Arrays.asList(sentence.split(" ")).forEach(s->{
//            KomoranResult analyze = komoran.analyze(
//                sentence);
//            analyze.getTokenList().stream().filter(t->poss.contains(t.getPos())).forEach(t->log.debug("{}",t));
//        //});
//
//    }
//
//    @Test
//    public void k_test2(){
//
//        final Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
//        final String sentence = "전라남도 장성군 북하면 약수리";
//        final Set<String> poss = Sets.newHashSet("VV", "NNP");
//
//        //Arrays.asList(sentence.split(" ")).forEach(s->{
//        KomoranResult analyze = komoran.analyze(
//            sentence);
//        analyze.getTokenList().stream()/*.filter(t->poss.contains(t.getPos()))*/.forEach(t->log.debug("{}",t));
//        //});
//    }

}
