package com.elm.mapper;

import com.elm.annotation.AutoFill;
import com.elm.entity.FileHistory;
import com.elm.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FileMapper {

    @Select("select * from filehistory where id = #{id}")
    FileHistory getFileById(Integer id);

    @AutoFill(value = OperationType.INSERT)
    @Insert("INSERT INTO filehistory (" +
            "id,filename,path,create_time,update_time,status,type,upload_author,uuid) values (" +
            "#{id},#{filename},#{createTime},#{updateTime},#{status},#{type},#{upload_author},#{uuid})")
    Integer InsertFile(FileHistory fileHistory);

    @AutoFill(value = OperationType.UPDATE)
    @Update("UPDATE filehistory set filename = #{filename},status = #{status},update_time = #{updateTime}")
    Integer UpdateFile(FileHistory fileHistory);

}
