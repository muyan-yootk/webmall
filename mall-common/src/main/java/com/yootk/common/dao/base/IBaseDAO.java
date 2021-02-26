package com.yootk.common.dao.base;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 定义数据操作的公共接口，利用该接口封装公共的数据处理方法
 * @param <ID> 描述不同数据表的主键类型
 * @param <VO> 描述不同数据表映射转换的VO类型
 * @author 李兴华（爆可爱的小李老师、沐言科技：www.yootk.com）
 */
public interface IBaseDAO<ID, VO> { // 描述公共接口
    /**
     * 实现数据信息的增加处理操作，该方法的主要功能是执行INSERT命令
     * @param vo 要增加数据所保存的VO对象实例
     * @return 数据增加成功返回true，否则返回false
     * @throws SQLException 当前进行的是SQL数据库操作，所以所有的异常全部都为SQLException
     */
    public boolean doCreate(VO vo) throws SQLException;

    /**
     * 实现数据修改操作处理，该方法的主要功能是执行UPDATE命令，此时根据主键修改数据
     * @param vo 要更改数据的VO对象实例
     * @return 数据修改成功返回true，否则返回false
     * @throws SQLException 数据库执行时所抛出的JDBC异常
     */
    public boolean doEdit(VO vo) throws SQLException;
    /**
     * 实现数据的批量删除操作，在删除的时候要求将所有要删除的数据ID封装在Set集合之中
     * @param ids 要删除的id集合
     * @return 删除指定数量的数据之后返回true，否则返回false
     * @throws SQLException 数据库执行时所抛出的JDBC异常
     */
    public boolean doRemove(Set<ID> ids) throws SQLException;
    /**
     * 根据ID查询完整数据信息，所有的信息通过VO实例进行包装
     * @param id 要查询的主键内容
     * @return 如果查询到指定的主键存在，则将数据以VO对象的实例形式返回，否则返回null
     * @throws SQLException 数据库执行时所抛出的JDBC异常
     */
    public VO findById(ID id) throws SQLException;

    /**
     * 查询全部的数据信息
     * @return 全部数据的List集合，如果该表没没有任何数据返回，集合为空集合（不是null，而是empty、集合长度为0）
     * @throws SQLException 数据库执行时所抛出的JDBC异常
     */
    public List<VO> findAll() throws SQLException;

    /**
     * 实现数据分页加载
     * @param currentPage 当前所在页
     * @param lineSize 每页显示的数据行数
     * @return 全部数据的List集合，如果该表没没有任何数据返回，集合为空集合（不是null，而是empty、集合长度为0）
     * @throws SQLException 数据库执行时所抛出的JDBC异常
     */
    public List<VO> findSplit(Integer currentPage, Integer lineSize) throws SQLException;

    /**
     * 实现数据分页加载，在进行加载时可以实现数据的模糊查询，查询中需要设置查询字段的名称
     * @param currentPage 当前所在页
     * @param lineSize 每页显示的数据行数
     * @param column 要查询的数据列名称
     * @param keyword 要查询的关键字
     * @return 全部数据的List集合，如果该表没没有任何数据返回，集合为空集合（不是null，而是empty、集合长度为0）
     * @throws SQLException 数据库执行时所抛出的JDBC异常
     */
    public List<VO> findSplit(Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException;

    /**
     * 进行数据表数据量全部个数统计
     * @return SQL中的count()统计函数的执行结果
     * @throws SQLException 数据库执行时所抛出的JDBC异常
     */
    public Long getAllCount() throws SQLException;

    /**
     * 进行数据表数据量全部个数统计
     * @param column 要查询的数据列名称
     * @param keyword 要查询的关键字
     * @return SQL中的count()统计函数的执行结果
     * @throws SQLException 数据库执行时所抛出的JDBC异常
     */
    public Long getAllCount(String column, String keyword) throws SQLException;
}
