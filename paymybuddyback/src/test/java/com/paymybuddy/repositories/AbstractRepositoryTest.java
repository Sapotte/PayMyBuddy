package com.paymybuddy.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-IC.yml")
public class AbstractRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute("""
                CREATE TABLE users(
                        id              Int  Auto_increment  NOT NULL ,
                        password        Varchar (20) NOT NULL ,
                        first_name      Varchar (50) NOT NULL ,
                        last_name       Varchar (100) NOT NULL ,
                        account_balance Float NOT NULL ,
                        email           Varchar (150) NOT NULL
                \t,CONSTRAINT users_AK UNIQUE (email)
                \t,CONSTRAINT users_PK PRIMARY KEY (id)
                )ENGINE=InnoDB;""");
        jdbcTemplate.execute("""
                CREATE TABLE extern_transactions(
                        id       Int  Auto_increment  NOT NULL ,
                        account  Varchar (50) NOT NULL ,
                        date     Date NOT NULL ,
                        type     Varchar (2) NOT NULL ,
                        amount   Float NOT NULL ,
                        id_users Int NOT NULL
                \t,CONSTRAINT extern_transactions_PK PRIMARY KEY (id)

                \t,CONSTRAINT extern_transactions_users_FK FOREIGN KEY (id_users) REFERENCES users(id)
                )ENGINE=InnoDB;""");
        jdbcTemplate.execute("""
                CREATE TABLE friendships(
                                            id         Int  Auto_increment  NOT NULL ,
                                            user1      Int NOT NULL ,
                                            user2      Int NOT NULL ,
                                            id_users   Int NOT NULL ,
                                            id_2_users Int NOT NULL
                    ,CONSTRAINT friendships_PK PRIMARY KEY (id)

                    ,CONSTRAINT friendships_users_FK FOREIGN KEY (id_users) REFERENCES users(id)
                    ,CONSTRAINT friendships_users_2_FK FOREIGN KEY (id_2_users) REFERENCES users(id)
                )ENGINE=InnoDB;""");
        jdbcTemplate.execute("""
                CREATE TABLE intern_transactions(
                        id             Int  Auto_increment  NOT NULL ,
                        date           Date NOT NULL ,
                        status         Varchar (2) NOT NULL ,
                        amount         Decimal (5,2) NOT NULL ,
                        label          Varchar (350) NOT NULL ,
                        sender_id      Int NOT NULL ,
                        id_friendships Int NOT NULL
                \t,CONSTRAINT intern_transactions_PK PRIMARY KEY (id)

                \t,CONSTRAINT intern_transactions_friendships_FK FOREIGN KEY (id_friendships) REFERENCES friendships(id)
                )ENGINE=InnoDB;""");
    }

    @PreDestroy
    public void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS intern_transactions");
        jdbcTemplate.execute("DROP TABLE IF EXISTS friendships");
        jdbcTemplate.execute("DROP TABLE IF EXISTS extern_transactions");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
    }
}
