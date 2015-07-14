package org.sfm.poi;


import org.apache.poi.ss.usermodel.Row;
import org.sfm.csv.CsvColumnKey;
import org.sfm.map.AbstractMapperBuilder;
import org.sfm.map.GetterFactory;
import org.sfm.map.Mapper;
import org.sfm.map.column.ColumnProperty;
import org.sfm.map.impl.context.MappingContextFactoryBuilder;
import org.sfm.map.impl.*;
import org.sfm.poi.impl.JoinSheetMapper;
import org.sfm.poi.impl.CsvColumnKeyRowKeySourceGetter;
import org.sfm.poi.impl.RowGetterFactory;
import org.sfm.poi.impl.StaticSheetMapper;
import org.sfm.reflect.meta.ClassMeta;

public class SheetMapperBuilder<T> extends AbstractMapperBuilder<Row, T, CsvColumnKey, RowMapper<T>,  SheetMapperBuilder<T>> {

    public static final MapperSourceImpl<Row, CsvColumnKey> FIELD_MAPPER_SOURCE =
            new MapperSourceImpl<Row, CsvColumnKey>(Row.class, new RowGetterFactory());


    public SheetMapperBuilder(ClassMeta<T> classMeta,
                              MapperConfig<CsvColumnKey, FieldMapperColumnDefinition<CsvColumnKey, Row>> mapperConfig,
                              GetterFactory<Row, CsvColumnKey> getterFactory) {
        super(classMeta, new MappingContextFactoryBuilder<Row, CsvColumnKey>(new CsvColumnKeyRowKeySourceGetter()), mapperConfig, FIELD_MAPPER_SOURCE.getterFactory(getterFactory), 0);
    }

    @Override
    protected CsvColumnKey key(String column, int index) {
        return new CsvColumnKey(column, index);
    }

    @Override
    protected RowMapper<T> newJoinJdbcMapper(Mapper<Row, T> mapper) {
        return new JoinSheetMapper<T>(mapper, mapperConfig.rowHandlerErrorHandler(), mappingContextFactoryBuilder.newFactory());
    }

    @Override
    protected RowMapper<T> newStaticJdbcMapper(Mapper<Row, T> mapper) {
        return  new StaticSheetMapper<T>(mapper, mapperConfig.rowHandlerErrorHandler(), mappingContextFactoryBuilder.newFactory());
    }
}