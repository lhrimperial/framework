###JPA Configuration:  
```
spring.jpa.database=MYSQL
# Show or not log for each sql query
spring.jpa.show-sql=false
spring.jpa.generate-ddl=true  
# Hibernate ddl auto (create, create-drop, update)
spring.jpa.hibernate.ddl-auto=create  
#spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect  
spring.jpa.hibernate.naming_strategy=org.hibernate.cfg.ImprovedNamingStrategy  
#spring.jpa.database=org.hibernate.dialect.MySQL5InnoDBDialect 
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

```