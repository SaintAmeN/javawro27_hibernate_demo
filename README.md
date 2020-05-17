# Run instructions
Please copy contents below to Your */src/main/resources/hibernate.cfg.xml*:
```
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="connection.url"> <!--hibernate_students = nazwa bazy danych-->
            jdbc:mysql://localhost:3306/hibernate_students?serverTimezone=Europe/Warsaw&amp;createDatabaseIfNotExist=true
        </property>
        <!-- createDatabaseIfNotExist=true = parametr powoduje stworzenie BAZY DANYCH-->
        <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <!--powyższa linia służy do wskazania hibernate'owi sterownika bazy danych-->

        <property name="connection.username">root</property>
        <property name="connection.password">root</property>

        <!--DB schema will be updated if needed -->
        <!--Hibernate model to data definition language-->
        <!--create - jeĹ›li ustawiony czyĹ›ci bazÄ™ danych przed uruchomieniem i od nowa tworzy tabele-->
        <!--update - jeĹ›li ustawiony dopisuje brakujÄ…ce elementy/tabele/kolumny do bazy -->
        <!--create-drop - uruchom connector, stwĂłrz tabele i kolumny, a po zakoĹ„czeniu aplikacji dropuj wszystkiego -->
        <!--validate - weryfikuje poprawnoĹ›Ä‡ bazy -->
        <!--ustawienie definiuje czy hibernate ma sam stworzyć TABELE! -->
        <property name="hbm2ddl.auto">update</property>
        <property name="show_sql">true</property>

        <!--TODO: na potem - dopisanie klas obsługiwanych przez hibernate -->
        <mapping class="com.sda.javawro27.hibernate.model.Student"/>
    </session-factory>
</hibernate-configuration>
```