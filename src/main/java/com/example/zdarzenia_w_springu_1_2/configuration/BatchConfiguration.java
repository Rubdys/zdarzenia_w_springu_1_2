package com.example.zdarzenia_w_springu_1_2.configuration;

import com.example.zdarzenia_w_springu_1_2.model.InputPerson;
import com.example.zdarzenia_w_springu_1_2.model.OutputPerson;
import com.example.zdarzenia_w_springu_1_2.processor.PersonProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {


    @Bean
    public FlatFileItemReader<InputPerson> reader(){
        FlatFileItemReader<InputPerson> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("upload/input.csv"));

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("name", "surname", "birthdate");

        BeanWrapperFieldSetMapper<InputPerson> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(InputPerson.class);

        DefaultLineMapper<InputPerson> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);

        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    public FlatFileItemWriter<OutputPerson> writer(){
        BeanWrapperFieldExtractor<OutputPerson> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[]{"name", "surname", "age"});

        DelimitedLineAggregator<OutputPerson> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(extractor);

        FlatFileItemWriter<OutputPerson> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("output.csv"));
        writer.setShouldDeleteIfExists(true);
        writer.setLineAggregator(aggregator);

        return writer;
    }

    @Bean
    public PersonProcessor processor(){
        return new PersonProcessor();
    }

    @Bean
    JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource());
        factory.setTransactionManager(transactionManager());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .build();
    }

    @Bean
    public Step calculateAge(
            JobRepository jobRepository,
            ItemReader<InputPerson> reader,
            ItemProcessor<InputPerson, OutputPerson> processor,
            ItemWriter<OutputPerson> writer,
            PlatformTransactionManager transactionManager
    ){
      return new StepBuilder("calculateAge")
              .repository(jobRepository)
              .transactionManager(transactionManager)
              .<InputPerson, OutputPerson>chunk(50)
              .reader(reader)
              .processor(processor)
              .writer(writer)
              .build();
    }

    @Bean
    public Job calculateAgeJob(Step calculateAge, JobRepository jobRepository){
        return new JobBuilder("calculateAgeJob")
                .repository(jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(calculateAge)
                .end()
                .build();
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

}
