/**
 
 
 package com.evergrande.util.conversion;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean转换工具类
 *
 * @since 1.0.0
 * @author zhaohai.wu
 * @date 2015年9月22日 上午10:36:56
 * 
 */
public final class BeanConvertUtils {

    private BeanConvertUtils() {
        super();
    }


    /**
     * 转换bean
     * 
     * @param source
     *            源对象
     * @param targetClass
     *            目标对象类
     * @param handler
     *            对象转换处理器
     * @return 转换后的bean
     *
     * @since 1.0.0
     */
    public static <K, T> K convert(T source, Class<K> targetClass, BeanConvertHandler<T, K> handler) {
        K target = null;

        if (source != null) {
            try {
                // 初始化bean
                target = targetClass.newInstance();

                // 简单的直接拷贝
                BeanUtils.copyProperties(source, target);

                // 如果存在自定义处理器则这些
                if (handler != null) {
                    handler.handle(source, target);
                }
            } catch (BeansException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        return target;
    }


    /**
     * 转换bean
     * 
     * @param source
     *            源对象
     * @param targetClass
     *            目标对象类
     * @return 转换后的bean
     *
     * @since 1.0.0
     */
    public static <K, T> K convert(T source, Class<K> targetClass) {
        return convert(source, targetClass, null);
    }


    /**
     * 转换bean列表
     * 
     * @param sources
     *            源对象列表
     * @param targetClass
     *            目标对象类
     * @param handler
     *            转换处理对象
     * @return 转换厚的的targetClass 对应 bean列表
     * 
     * @since 1.0.0
     */
    public static <K, T> List<K> convertList(List<T> sources, Class<K> targetClass, BeanConvertHandler<T, K> handler) {
        List<K> targets = null;

        if (sources != null) {
            targets = new ArrayList<>(sources.size());

            // 循环转换
            K target;
            for (T source : sources) {
                target = convert(source, targetClass, handler);
                if (target != null) {
                    targets.add(target);
                }
            }
        }

        return targets;
    }


    /**
     * 转换bean列表
     * 
     * @param sources
     *            源对象列表
     * @param targetClass
     *            目标对象类
     * @return 转换厚的的targetClass 对应 bean列表
     * 
     * @since 1.0.0
     */
    public static <K, T> List<K> convertList(List<T> sources, Class<K> targetClass) {
        return convertList(sources, targetClass, null);
    }
}



 * */

package com.evergrande.mj.promotionserver.mapper;

import com.evergrande.mj.promotionserver.model.PromotionActConditionModel;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.github.pagehelper.Page;

/**
 *
 * 联单活动条件
 *
 * @author czx
 * @date 2018-08-17 15:31:37
 */
@Mapper
public interface PromotionActConditionMapper {
	
	
	pageHelper 5.xxx 

	 /**
	   * 新增
	   * @param promotionActConditionModel
	   * @return int
	   */
	public int save(@Param("promotionActConditionModel")PromotionActConditionModel  promotionActConditionModel);
	
	
	

	/**
	   *批量新增
	   * @param listPromotionActConditionModel
	   * @return int
	   */
	public int saveList(@Param("listPromotionActConditionModel")List<PromotionActConditionModel>  listPromotionActConditionModel);
	

  	 /**
	   * 查询详情
	   * @param promotionActConditionModel
	   * @return
	   */
	public PromotionActConditionModel queryDetail(@Param("promotionActConditionModel")PromotionActConditionModel promotionActConditionModel);
  
	/**
	 * 查询列表
	 * @param promotionActConditionModel
	 * @return List<PromotionActConditionModel>
	 */
	public List<PromotionActConditionModel> queryList(@Param("promotionActConditionModel")PromotionActConditionModel promotionActConditionModel);
	  
	/**
	 * 分页查询列表
	 * @param promotionActConditionModel
	 * @return Page<PromotionActConditionModel>
	 */
	public Page<PromotionActConditionModel> queryListByPage(@Param("promotionActConditionModel")PromotionActConditionModel promotionActConditionModel);
	
	  
	/**
	 * 更新
	 * @param promotionActConditionModel
	 * @return int
	 */
	public int update(@Param("promotionActConditionModel")PromotionActConditionModel promotionActConditionModel);
	  
	 /**
	  * 物理删除 
	  * @param promotionActConditionModel
	  */
	public int delete(@Param("promotionActConditionModel")PromotionActConditionModel promotionActConditionModel);
	
	/**
	  * 根据入参条件查询 总数
	  * @param promotionActConditionModel
	  */
	public Long queryCount(@Param("promotionActConditionModel")PromotionActConditionModel promotionActConditionModel);




	public void updateBySql(@Param("promotionActConditionModel")PromotionActConditionModel promotionActConditionModel);
	
	
/**
 * 
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.evergrande.mj.promotionserver.mapper.PromotionActConditionMapper">

        <!-- 通用查询映射结果 -->
        <resultMap id="baseResultMap" type="com.evergrande.mj.promotionserver.model.PromotionActConditionModel">
                <result property="conditionId" column="condition_id"/>
                <result property="actId" column="act_id"/>
                <result property="fullKey" column="full_key"/>
                <result property="fullValue" column="full_value"/>
                <result property="fullValueCount" column="full_value_count"/>
                <result property="isDelete" column="is_delete"/>
                <result property="createdBy" column="created_by"/>
                <result property="createdTime" column="created_time"/>
                <result property="updatedBy" column="updated_by"/>
                <result property="updatedTime" column="updated_time"/>
        </resultMap>



    <sql id="baseColumns">

        condition_id ,
        act_id ,
        full_key ,
        full_value ,
        full_value_count ,
        is_delete ,
        created_by ,
        created_time ,
        updated_by ,
        updated_time 

</sql>


    <sql id="queryConditions">
        WHERE 1=1

                <if test="promotionActConditionModel.conditionId > 0 ">
                    AND condition_id = #{promotionActConditionModel.conditionId} 
                </if>

                <if test="promotionActConditionModel.actId > 0 ">
                    AND act_id = #{promotionActConditionModel.actId} 
                </if>

                <if test="promotionActConditionModel.fullKey > 0 ">
                    AND full_key = #{promotionActConditionModel.fullKey} 
                </if>

                <if test="promotionActConditionModel.fullValue > 0 ">
                    AND full_value = #{promotionActConditionModel.fullValue} 
                </if>

                <if test="promotionActConditionModel.fullValueCount > 0 ">
                    AND full_value_count = #{promotionActConditionModel.fullValueCount} 
                </if>

                <if test="promotionActConditionModel.isDelete > 0 ">
                    AND is_delete = #{promotionActConditionModel.isDelete} 
                </if>

                <if test="promotionActConditionModel.createdBy > 0 ">
                    AND created_by = #{promotionActConditionModel.createdBy} 
                </if>

                <if test="promotionActConditionModel.createdTime > 0 ">
                    AND created_time = #{promotionActConditionModel.createdTime} 
                </if>

                <if test="promotionActConditionModel.updatedBy > 0 ">
                    AND updated_by = #{promotionActConditionModel.updatedBy} 
                </if>

                <if test="promotionActConditionModel.updatedTime > 0 ">
                    AND updated_time = #{promotionActConditionModel.updatedTime} 
                </if>
                
                
                
                <if test="promotionActOrderModel.actName != null and promotionActOrderModel.actName != ''">
                   and act_id in (select act_id from t_promotion_activity where act_name  LIKE CONCAT('%',#{promotionActOrderModel.actName},'%')) 
                </if>


    </sql>


    <sql id="limit">
        <if test="page != null">
            limit #{page.start},#{page.pageSize}
        </if>
    </sql>


    <insert id="save" parameterType="com.evergrande.mj.promotionserver.model.PromotionActConditionModel" useGeneratedKeys="true"
            keyProperty="ConditionId" keyColumn="condition_id">
        insert into t_promotion_act_condition
        <trim prefix="(" suffix=")" suffixOverrides=",">



                    <if test="promotionActConditionModel.conditionId >0">
                        condition_id  ,
                    </if>

                    <if test="promotionActConditionModel.actId >0">
                        act_id  ,
                    </if>

                    <if test="promotionActConditionModel.fullKey >0">
                        full_key  ,
                    </if>

                    <if test="promotionActConditionModel.fullValue >0">
                        full_value  ,
                    </if>

                    <if test="promotionActConditionModel.fullValueCount >0">
                        full_value_count  ,
                    </if>

                    <if test="promotionActConditionModel.isDelete != null">
                        is_delete  ,
                    </if>

                    <if test="promotionActConditionModel.createdBy >0">
                        created_by  ,
                    </if>

                    <if test="promotionActConditionModel.createdTime >0">
                        created_time  ,
                    </if>

                    <if test="promotionActConditionModel.updatedBy >0">
                        updated_by  ,
                    </if>

                    <if test="promotionActConditionModel.updatedTime >0">
                        updated_time  
                    </if>

        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">


                    <if test="promotionActConditionModel.conditionId >0">
                        #{promotionActConditionModel.conditionId} ,
                    </if>

                    <if test="promotionActConditionModel.actId >0">
                        #{promotionActConditionModel.actId} ,
                    </if>

                    <if test="promotionActConditionModel.fullKey >0">
                        #{promotionActConditionModel.fullKey} ,
                    </if>

                    <if test="promotionActConditionModel.fullValue >0">
                        #{promotionActConditionModel.fullValue} ,
                    </if>

                    <if test="promotionActConditionModel.fullValueCount >0">
                        #{promotionActConditionModel.fullValueCount} ,
                    </if>

                    <if test="promotionActConditionModel.isDelete != null">
                        #{promotionActConditionModel.isDelete} ,
                    </if>

                    <if test="promotionActConditionModel.createdBy >0">
                        #{promotionActConditionModel.createdBy} ,
                    </if>

                    <if test="promotionActConditionModel.createdTime >0">
                        #{promotionActConditionModel.createdTime} ,
                    </if>

                    <if test="promotionActConditionModel.updatedBy >0">
                        #{promotionActConditionModel.updatedBy} ,
                    </if>

                    <if test="promotionActConditionModel.updatedTime >0">
                        #{promotionActConditionModel.updatedTime} 
                    </if>


        </trim>
    </insert>


    <insert id="saveList" parameterType="com.evergrande.mj.promotionserver.model.PromotionActConditionModel">
        insert into  t_promotion_act_condition
        <trim prefix="(" suffix=")" suffixOverrides=",">
                        act_id ,
                        full_key ,
                        full_value ,
                        full_value_count ,
                        is_delete ,
                        created_by ,
                        created_time 

        </trim>
        VALUES
        <foreach collection="listPromotionActConditionModel" item="promotionActConditionModel" index="index"
                 separator=",">
            (
                        #{promotionActConditionModel.actId} ,
                        #{promotionActConditionModel.fullKey} ,
                        #{promotionActConditionModel.fullValue} ,
                        #{promotionActConditionModel.fullValueCount} ,
                        #{promotionActConditionModel.isDelete} ,
                        #{promotionActConditionModel.createdBy} ,
                        #{promotionActConditionModel.createdTime} 
            )
        </foreach>
    </insert>


    <select id="queryDetail" parameterType="com.evergrande.mj.promotionserver.model.PromotionActConditionModel"
            resultMap="baseResultMap">
        select
        <include refid="baseColumns"/>
        from t_promotion_act_condition
        <include refid="queryConditions"/>
    </select>


    <select id="queryList" resultMap="baseResultMap"
            parameterType="com.evergrande.mj.promotionserver.model.PromotionActConditionModel">
        select
        <include refid="baseColumns"/>
        from t_promotion_act_condition
        <include refid="queryConditions"/>
        order by condition_id
    </select>

    <select id="queryListByPage" resultMap="baseResultMap"
            parameterType="com.evergrande.mj.promotionserver.model.PromotionActConditionModel">
        select
        <include refid="baseColumns"/>
        from t_promotion_act_condition
        <include refid="queryConditions"/>
    </select>


    <select id="queryCount" parameterType="com.evergrande.mj.promotionserver.model.PromotionActConditionModel"
            resultType="java.lang.Long">
        SELECT COUNT(1) FROM t_promotion_act_condition
        <include refid="queryConditions"/>
    </select>


    <update id="update" parameterType="com.evergrande.mj.promotionserver.model.PromotionActConditionModel">
        update t_promotion_act_condition
        <set>

                    <if test="promotionActConditionModel.conditionId > 0">
                        condition_id = #{promotionActConditionModel.conditionId} ,
                    </if>


                    <if test="promotionActConditionModel.actId > 0">
                        act_id = #{promotionActConditionModel.actId} ,
                    </if>


                    <if test="promotionActConditionModel.fullKey > 0">
                        full_key = #{promotionActConditionModel.fullKey} ,
                    </if>


                    <if test="promotionActConditionModel.fullValue > 0">
                        full_value = #{promotionActConditionModel.fullValue} ,
                    </if>


                    <if test="promotionActConditionModel.fullValueCount > 0">
                        full_value_count = #{promotionActConditionModel.fullValueCount} ,
                    </if>


                    <if test="promotionActConditionModel.isDelete >0">
                        is_delete = #{promotionActConditionModel.isDelete} ,
                    </if>


                    <if test="promotionActConditionModel.createdBy > 0">
                        created_by = #{promotionActConditionModel.createdBy} ,
                    </if>


                    <if test="promotionActConditionModel.createdTime > 0">
                        created_time = #{promotionActConditionModel.createdTime} ,
                    </if>


                    <if test="promotionActConditionModel.updatedBy > 0">
                        updated_by = #{promotionActConditionModel.updatedBy} ,
                    </if>


                    <if test="promotionActConditionModel.updatedTime > 0">
                        updated_time = #{promotionActConditionModel.updatedTime} 
                    </if>

        </set>
        where condition_id = #{promotionActConditionModel.conditionId}
    </update>
    
    
    
       <update id="updateBySql" parameterType="com.evergrande.mj.promotionserver.model.PromotionActConditionModel">
        update t_promotion_act_condition
        <set>
                    is_delete = #{promotionActConditionModel.isDelete}
        </set>
        where act_id = #{promotionActConditionModel.actId}
    </update>
    
    
    
        <update id="updateBatch" >
        update t_package
        <set>
            <if test="packageModel.status!=null">
                status = #{packageModel.status} ,
            </if>
            <if test="packageModel.isTop!=null">
                is_top = #{packageModel.isTop} ,
            </if>
            updated_time = #{packageModel.updatedTime}
        </set>
        where package_id in
        <foreach collection="packageIds" item="packageId" index="index" separator="," open="(" close=")">
            #{packageId}
        </foreach>
    </update>
    
    
    
    
    
    <update id="frozenInventory">
        update t_goods_sku_attr
        set occupy_num =
        <foreach collection="list" item="item" index="index" separator=" " open="case" close="end">
            WHEN (sku_id = #{item.skuId} and remain_num >= #{item.num})
            Then occupy_num + #{item.num}
        </foreach>,
        status =
        <foreach collection="list" item="item" index="index" separator=" " open="case" close="else status end">
            WHEN (sku_id = #{item.skuId} and remain_num = #{item.num})
            then 3
        </foreach>,
        remain_num =
        <foreach collection="list" item="item" index="index" separator=" " open="case" close="end">
            WHEN (sku_id = #{item.skuId} and remain_num >= #{item.num})
            then remain_num - #{item.num}
        </foreach>,
        updated_by= #{userId},
        updated_time=#{currentTime}
        where
        <foreach collection="list" item="item" separator="or">
            (sku_id = #{item.skuId} and remain_num >= #{item.num})
        </foreach>
    </update>
    


    <delete id="delete" parameterType="com.evergrande.mj.promotionserver.model.PromotionActConditionModel">
        delete from t_promotion_act_condition

        <include refid="queryConditions"/>
    </delete>


</mapper>

 * 
 * */
	
	  

}