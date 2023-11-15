package com.project.dugeun.config;

import com.project.dugeun.domain.blindDate.domain.Match;
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
import com.project.dugeun.domain.blindDate.application.MatchService;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class ChunkJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final MatchService matchService;

    @Bean
    public Job ChunkJob() throws Exception {
        return jobBuilderFactory.get("ChunkJob0")
                .start(step())
                .build();
    }

    @Bean
    @JobScope
    public Step step() throws Exception {
        return stepBuilderFactory.get("step")
                .<User, List<Match>>chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writerList())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<User> reader() throws Exception{
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
    public ItemProcessor<User, List<Match>> processor()
    {
        log.info("ItemProcessor 실행됨");
        return new ItemProcessor<User, List<Match>>() {
            @Override
            public List<Match> process(User user) throws Exception
            {
                // 해당 user의 다른 사용자들에 대한 match 점수들 계산해서 얻어낸 match들 return
                return matchService.calculateMatches(user);
            }

        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<List<Match>> writer()
    {

        log.info("ItemWriter 실행됨");
        return new JpaItemWriterBuilder<List<Match>>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    @StepScope
    public JpaItemListWriter<Match> writerList(){
        JpaItemWriter<Match> writer  = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);

        JpaItemListWriter<Match> jpaItemListWriter = new JpaItemListWriter<>(writer);
        jpaItemListWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemListWriter;

    }

}
