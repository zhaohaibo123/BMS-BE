package meng.xing.service;

import meng.xing.entity.Subject;
import meng.xing.entity.TestItem;
import meng.xing.repository.TestItemRepository;
import meng.xing.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultTestItem implements TestItemService {
    final Logger logger = LoggerFactory.getLogger(DefaultTestItem.class);
    @Autowired
    TestItemRepository testItemRepository;
    @Autowired
    UserRoleRepository userRoleRepository;


    @Override
    public Page<TestItem> findTestItemsByTypeAndSubject(String type, Subject subject, Pageable pageable) {
        if (subject ==null){
            return testItemRepository.findByType(type,pageable);
        }
        return testItemRepository.findByTypeAndSubject(type,subject,pageable);
    }

    @Override
    @Transactional
    public boolean addTestItme(TestItem testItem) {
        if (testItemRepository.save(testItem) != null)
            return true;
        else return false;
    }

    @Override
    @Transactional
    public boolean updateTestItem(TestItem testItem) {
        if (testItemRepository.save(testItem) != null)
            return true;
        else return false;

    }

    @Override
    @Transactional
    public boolean deleteTestItemById(Long id) {
        testItemRepository.delete(id);
        return true;
    }

    @Override
    public TestItem findTestItemById(Long id) {
        return testItemRepository.findOne(id);
    }
}
