package br.com.tricoli.db;

import br.com.tricoli.core.entity.Distance;
import br.com.tricoli.core.entity.Map;
import br.com.tricoli.core.repository.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by luigitricoli on 8/4/15.
 */
public class MapsSqlite3 implements Maps {

    private static final Logger log = LoggerFactory.getLogger(MapsSqlite3.class);
    public static final String SELECT_FROM_MAPS_WHERE_NAME = "select * from MAPS where NAME = ?";
    public static final String INSERT_INTO_MAPS_NAME_SOURCE_TARGET_DISTANCE_VALUES = "insert into MAPS (NAME, SOURCE, TARGET, DISTANCE) values (?,?,?,?)";

    @Inject
    private ConnSqlite3 sqlite3;

    /**
     * Constructs new empty {@code MapsSqlite3}
     */
    public MapsSqlite3() {
        log.debug("Create");
    }

    /**
     * @see {@link br.com.tricoli.core.repository.Maps#get(String)}
     */
    @Override
    public Map get(String name) {
        Map map = new Map(name);
        log.debug(SELECT_FROM_MAPS_WHERE_NAME.replace("?", name));
        try(PreparedStatement query = sqlite3.getCon().prepareStatement(SELECT_FROM_MAPS_WHERE_NAME);) {

            query.setString(1, name);
            ResultSet result = query.executeQuery();

            while (result.next()) {
                map.addDistance(new Distance(result.getString("SOURCE"), result.getString("TARGET"), result.getInt("DISTANCE")));
            }

        }catch (SQLException e){
            log.error(e.getMessage(), e);
        }


        return map;
    }

    /**
     * @see {@link br.com.tricoli.core.repository.Maps#add(br.com.tricoli.core.entity.Map)}
     */
    @Override
    public void add(Map map) throws SQLException {
        try(PreparedStatement insert = sqlite3.getCon().prepareStatement(INSERT_INTO_MAPS_NAME_SOURCE_TARGET_DISTANCE_VALUES);) {
            for (Distance distance : map.getDistances()) {
                log.debug(INSERT_INTO_MAPS_NAME_SOURCE_TARGET_DISTANCE_VALUES.replace("?", "{}"),
                        map.getName(), distance.getSource(), distance.getTarget(), distance.getDistance());

                insert.setString(1, map.getName());
                insert.setString(2, distance.getSource());
                insert.setString(3, distance.getTarget());
                insert.setInt(4, distance.getDistance());
                insert.addBatch();
            }
            insert.executeBatch();
            sqlite3.getCon().commit();
        } catch (SQLException e) {
            throw e;
        }
    }

}
