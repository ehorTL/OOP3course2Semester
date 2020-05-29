create table address(
    aId SERIAL PRIMARY KEY,
    country CHARACTER VARYING(50),  
    countryCode CHARACTER(2),
    cityAreaOrDistrict CHARACTER VARYING(50),
    cityTownVillage CHARACTER VARYING(50),
    street CHARACTER VARYING (50),
    houseNumber CHARACTER VARYING (20),
    postalCode CHARACTER VARYING (20),
    extraInfo CHARACTER VARYING (20)
);

create table reader(
    rid SERIAL PRIMARY KEY,
    firstName CHARACTER VARYING(40),
    lastName CHARACTER VARYING(40),
    address INTEGER
);
alter table reader 
ADD CONSTRAINT fk_reader_address FOREIGN KEY (address) REFERENCES address (aId) ON DELETE SET NULL;


create table contactPhoneNumber(
    cnid serial primary key,
    rid integer,
    phoneNumber character varying(20)
);
alter table contactPhoneNumber 
ADD CONSTRAINT fk_contactPhoneNumber_rid FOREIGN KEY (rid) REFERENCES reader (rId) ON DELETE CASCADE;

create table contactMail(
    cmid serial primary key,
    rid integer,
    email character varying(30)
);
alter table contactMail 
ADD constraint fk_contactmail_rid FOREIGN KEY (rid) REFERENCES reader (rId) ON DELETE CASCADE;

create table periodical(
    pid serial primary key,
    name character varying(100),
    frequency numeric(5, 2)
);


create table payment(
    pid serial primary key,
    toAccount CHARACTER VARYING(100),
    sum numeric(20,2),
    currencyCode CHARACTER(3),
    paymentDateTime TIMESTAMP WITH TIME ZONE,
    description CHARACTER VARYING(200),
    rid integer,
    constraint fk_peyment_rid foreign key (rid) references reader (rId) ON DELETE SET NULL
);

create table subscriptionPer(
    sid serial primary key,
    quantity int,
    ispaid boolean,
    termInMonth integer,
    fromDate Date,
    paymentId integer,
    readerId integer,
    periodicalId integer,
    constraint fk_subs_rid foreign key (readerId) references reader(rId) on delete cascade,
    constraint fk_subs_perid foreign key (periodicalId) references periodical(pid) on delete cascade
);
alter table subscriptionPer 
add constraint fk_subs_paymentid foreign key (paymentId) references payment(pid) on delete set NULL;

alter table periodical 
add column priceperone numeric(10, 2);

alter table periodical 
add column descr character varying(500);

alter table payment  
add column fromAccount character varying(100);