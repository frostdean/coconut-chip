package com.frostdean.infrastructure

import com.frostdean.infrastructure.jpa.JpaBrandRepository
import com.frostdean.infrastructure.jpa.JpaCategoryRepository
import com.frostdean.infrastructure.jpa.JpaProductRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import kotlin.test.assertNotNull

@ActiveProfiles("test")
@TestPropertySource(locations = ["classpath:application-test.yaml"])
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [TestBootApp::class])
class JpaLoadTest(

) {
    @Autowired
    lateinit var jpaBrandRepository: JpaBrandRepository
    @Autowired
    lateinit var jpaProductRepository: JpaProductRepository
    @Autowired
    lateinit var jpaCategoryRepository: JpaCategoryRepository

    @Test
    fun test_load() {
        assertNotNull(jpaBrandRepository)
        assertNotNull(jpaProductRepository)
        assertNotNull(jpaCategoryRepository)
    }

}