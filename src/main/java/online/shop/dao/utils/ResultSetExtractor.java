package online.shop.dao.utils;

import online.shop.model.entity.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by andri on 1/17/2017.
 */
public interface ResultSetExtractor<E extends BaseEntity> {
    E extract(ResultSet set) throws SQLException;
}
