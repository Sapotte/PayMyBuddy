#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: users
#------------------------------------------------------------

CREATE TABLE users(
        id              Int  Auto_increment  NOT NULL ,
        password        Varchar (20) NOT NULL ,
        first_name      Varchar (50) NOT NULL ,
        last_name       Varchar (100) NOT NULL ,
        account_balance Float NOT NULL ,
        email           Varchar (150) NOT NULL
	,CONSTRAINT users_AK UNIQUE (email)
	,CONSTRAINT users_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: extern_transactions
#------------------------------------------------------------

CREATE TABLE extern_transactions(
        id       Int  Auto_increment  NOT NULL ,
        account  Varchar (50) NOT NULL ,
        date     Date NOT NULL ,
        type     Varchar (2) NOT NULL ,
        amount   Float NOT NULL ,
        id_users Int NOT NULL
	,CONSTRAINT extern_transactions_PK PRIMARY KEY (id)

	,CONSTRAINT extern_transactions_users_FK FOREIGN KEY (id_users) REFERENCES users(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: friendships
#------------------------------------------------------------

CREATE TABLE friendships(
                            id         Int  Auto_increment  NOT NULL ,
                            user1      Int NOT NULL ,
                            user2      Int NOT NULL ,
                            id_users   Int NOT NULL ,
                            id_2_users Int NOT NULL
    ,CONSTRAINT friendships_PK PRIMARY KEY (id)

    ,CONSTRAINT friendships_users_FK FOREIGN KEY (id_users) REFERENCES users(id)
    ,CONSTRAINT friendships_users_2_FK FOREIGN KEY (id_2_users) REFERENCES users(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: intern_transactions
#------------------------------------------------------------

CREATE TABLE intern_transactions(
        id             Int  Auto_increment  NOT NULL ,
        date           Date NOT NULL ,
        status         Varchar (2) NOT NULL ,
        amount         Decimal (5,2) NOT NULL ,
        label          Varchar (350) NOT NULL ,
        sender_id      Int NOT NULL ,
        id_friendships Int NOT NULL
	,CONSTRAINT intern_transactions_PK PRIMARY KEY (id)

	,CONSTRAINT intern_transactions_friendships_FK FOREIGN KEY (id_friendships) REFERENCES friendships(id)
)ENGINE=InnoDB;

