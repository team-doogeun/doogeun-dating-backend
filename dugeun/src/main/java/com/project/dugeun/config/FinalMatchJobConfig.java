package com.project.dugeun.config;


import com.project.dugeun.domain.finalMatch.application.FinalMatchService;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class FinalMatchJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final FinalMatchService finalMatchService;

    @Bean
    public Job finalMatchJob() throws Exception {
        return jobBuilderFactory.get("finalMatchChunkJob")
                .start(finalMatchStep())
                .build();
    }

    @Bean
    @JobScope
    public Step finalMatchStep() throws Exception {

        return stepBuilderFactory.get("step")
                .<User,List<FinalMatch>>chunk(2)
                .reader(finalMatchReader())
                .processor(finalMatchProcessor())
                .writer(finalMatchListWriter())
                .build();

    }

    @Bean
    @StepScope
    public JpaPagingItemReader<User> finalMatchReader() throws Exception{
        Map<String,Object> parameterValues = new HashMap<>();
        log.info("ItemReader 실행됨");
        return new JpaPagingItemReaderBuilder<User>()
                .pageSize(2)
                .parameterValues(parameterValues)
                .queryString("SELECT m FROM User m")
                .entityManagerFactory(entityManagerFactory)
                .name("JpaPagingItemReader")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<User, List<FinalMatch>> finalMatchProcessor()
    {
        log.info("ItemProcessor 실행됨");
        return new ItemProcessor<User, List<FinalMatch>>() {
            @Override
            public List<FinalMatch> process(User user) throws Exception
            {
                return finalMatchService.confirmFinalMatch(user.getUserId());
            }

        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<List<FinalMatch>> finalMatchWriter()
    {

        log.info("ItemWriter 실행됨");
        return new JpaItemWriterBuilder<List<FinalMatch>>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    @StepScope
    public JpaItemListWriter<FinalMatch> finalMatchListWriter(){
        JpaItemWriter<FinalMatch> writer  = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);

        JpaItemListWriter<FinalMatch> jpaItemListWriter = new JpaItemListWriter<>(writer);
        jpaItemListWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemListWriter;

    }

}
