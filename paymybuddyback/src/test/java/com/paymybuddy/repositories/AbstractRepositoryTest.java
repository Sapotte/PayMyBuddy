package com.paymybuddy.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AbstractRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute("CREATE TABLE users(\n" +
                "        id              Int  Auto_increment  NOT NULL ,\n" +
                "        password        Varchar (20) NOT NULL ,\n" +
                "        first_name      Varchar (50) NOT NULL ,\n" +
                "        last_name       Varchar (100) NOT NULL ,\n" +
                "        account_balance Float NOT NULL ,\n" +
                "        email           Varchar (150) NOT NULL\n" +
                "\t,CONSTRAINT users_AK UNIQUE (email)\n" +
                "\t,CONSTRAINT users_PK PRIMARY KEY (id)\n" +
                ")ENGINE=InnoDB;");
        jdbcTemplate.execute("CREATE TABLE extern_transactions(\n" +
                "        id       Int  Auto_increment  NOT NULL ,\n" +
                "        account  Varchar (50) NOT NULL ,\n" +
                "        date     Date NOT NULL ,\n" +
                "        type     Varchar (2) NOT NULL ,\n" +
                "        amount   Float NOT NULL ,\n" +
                "        id_users Int NOT NULL\n" +
                "\t,CONSTRAINT extern_transactions_PK PRIMARY KEY (id)\n" +
                "\n" +
                "\t,CONSTRAINT extern_transactions_users_FK FOREIGN KEY (id_users) REFERENCES users(id)\n" +
                ")ENGINE=InnoDB;");
        jdbcTemplate.execute("CREATE TABLE friendships(\n" +
                "                            id         Int  Auto_increment  NOT NULL ,\n" +
                "                            user1      Int NOT NULL ,\n" +
                "                            user2      Int NOT NULL ,\n" +
                "                            id_users   Int NOT NULL ,\n" +
                "                            id_2_users Int NOT NULL\n" +
                "    ,CONSTRAINT friendships_PK PRIMARY KEY (id)\n" +
                "\n" +
                "    ,CONSTRAINT friendships_users_FK FOREIGN KEY (id_users) REFERENCES users(id)\n" +
                "    ,CONSTRAINT friendships_users_2_FK FOREIGN KEY (id_2_users) REFERENCES users(id)\n" +
                ")ENGINE=InnoDB;");
        jdbcTemplate.execute("CREATE TABLE intern_transactions(\n" +
                "        id             Int  Auto_increment  NOT NULL ,\n" +
                "        date           Date NOT NULL ,\n" +
                "        status         Varchar (2) NOT NULL ,\n" +
                "        amount         Decimal (5,2) NOT NULL ,\n" +
                "        label          Varchar (350) NOT NULL ,\n" +
                "        sender_id      Int NOT NULL ,\n" +
                "        id_friendships Int NOT NULL\n" +
                "\t,CONSTRAINT intern_transactions_PK PRIMARY KEY (id)\n" +
                "\n" +
                "\t,CONSTRAINT intern_transactions_friendships_FK FOREIGN KEY (id_friendships) REFERENCES friendships(id)\n" +
                ")ENGINE=InnoDB;");
    }

    @PreDestroy
    public void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS intern_transactions");
        jdbcTemplate.execute("DROP TABLE IF EXISTS friendships");
        jdbcTemplate.execute("DROP TABLE IF EXISTS extern_transactions");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
    }
}
