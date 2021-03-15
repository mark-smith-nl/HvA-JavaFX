package practicum.dao;

import practicum.models.Country;
import practicum.utils.PersistencyConfiguration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryPersistentObjectDao extends AbstractPersistentObjectDao<Country> {

    @Override
    protected String getFileExtension() {
        return ".Country";
    }

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println(PersistencyConfiguration.TEMP_DIR);
        Country country = new Country(32, "Nederland", "NL", "Wordt ook wel Holland genoemd.", LocalDate.of(100, 1, 2), true);
        CountryPersistentObjectDao dao = new CountryPersistentObjectDao();
        dao.insert(country);

        List<Country> all = dao.getAll();
        all.forEach(System.out::println);
      //  dao.remove(new Country(32, ""));
        System.out.println();

       // all = dao.getAll();
       // all.forEach(System.out::println);
        all.forEach(c -> {
                dao.remove(c);
        });

        all.forEach(System.out::println);
    }
}
