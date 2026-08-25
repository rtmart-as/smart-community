package com.southwind.service;

import com.southwind.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.form.PersonListForm;
import com.southwind.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-07-26
 */
public interface PersonService extends IService<Person> {
    public PageVO personList(PersonListForm personListForm);
}
