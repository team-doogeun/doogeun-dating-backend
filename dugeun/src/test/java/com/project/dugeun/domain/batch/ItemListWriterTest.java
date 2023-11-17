package com.project.dugeun.domain.batch;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.user.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBatchTest
@SpringBootTest
public class ItemListWriterTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;


}
