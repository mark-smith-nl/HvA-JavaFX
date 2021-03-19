package practicum.dao.file;

import practicum.dao.AbstractDao;
import practicum.models.AbstractModel;
import practicum.utils.ApplicationConfiguration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
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
public abstract class AbstractPersistentObjectDao<M extends AbstractModel> implements AbstractDao<M> {

    protected abstract String getFileExtension();

    private long highestId = 0;

    @Override
    public List<M> getAll() {
        List<M> entities = new ArrayList<>();

        getAllEntityFiles().forEach(file -> {
            try (ObjectInputStream inputStreamStream = new ObjectInputStream(new FileInputStream(file))) {
                entities.add((M) inputStreamStream.readObject());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        return entities;
    }

    @Override
    public M getById(long id) {
        File file = getFileForEntityWithId(id);
        if (!file.exists()) return null;
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (M) inputStream.readObject();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void insert(M entity)  {
        if (entity.isNewEntity()) entity.setId(++highestId);
        File file = new File(ApplicationConfiguration.TEMP_DIR, "_" + entity.getId() + getParentId(null) + "." + entity.getClass().getSimpleName());
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(entity);
            System.out.printf("Persisted %s in %s\n", entity, file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(format("File %s was not found", file.getAbsolutePath()), e);
        } catch (IOException e) {
            throw new IllegalStateException(format("Could not write to file %s was not found", file.getAbsolutePath()), e);
        }
    }

    @Override
    public void update(M entity) {
        insert(entity);
    }

    @Override
    public void remove(M entity) {
        File file = getFileForEntity(entity);
        if (!file.exists()) return;
        file.delete();
    }

    protected String getParentId(M entity) {
        return "";
    }

    private Set<File> getAllEntityFiles() {
        try (Stream<Path> entries = Files.list(Paths.get(ApplicationConfiguration.TEMP_DIR))) {
            return entries
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toFile)
                    .filter(p -> p.getName().endsWith(getFileExtension()))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new IllegalStateException(format("Could not read directory %s", ApplicationConfiguration.TEMP_DIR), e);
        }
    }

    private File getFileForEntity(M entity) {
        return getFileForEntityWithId(entity.getId());
    }

    private File getFileForEntityWithId(long id) {
        return Paths.get(ApplicationConfiguration.TEMP_DIR + "/_" + id + getFileExtension()).toFile();
    }

    @Override
    public long getHighestId(M... entities) throws SQLException {
        for (M entity : entities) {
            if (!entity.isNewEntity()) {
                highestId = Math.max(highestId, entity.getId());
            }
        }

        return highestId;
    }
}
