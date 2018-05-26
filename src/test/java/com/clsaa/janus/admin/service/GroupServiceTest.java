package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.BaseTest;
import com.clsaa.janus.admin.entity.vo.v1.GroupV1;
import com.clsaa.janus.admin.result.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/19
 */
public class GroupServiceTest extends BaseTest {
    @Autowired
    private GroupService groupService;

    @Test
    public void addGroup() {
        String loginUserId = "1";
        String regionId = "1";
        String name = "name";
        String description = "description";
        String subDomain = "www.baidu.com";
        Integer trafficLimit = 100;
        String id = this.groupService.addGroup(loginUserId, regionId, name, description, subDomain, trafficLimit);
        GroupV1 groupV1 = this.groupService.getGroupV1ById("1", id);
        assertNotNull(groupV1);
        assertEquals(id, groupV1.getId());
        assertEquals(regionId, groupV1.getRegionId());
        assertEquals(name, groupV1.getName());
        assertEquals(description, groupV1.getDescription());
        assertEquals(subDomain, groupV1.getSubDomain());
        assertEquals(trafficLimit, groupV1.getTrafficLimit());
    }

    @Test
    public void delGroupById() {
        String loginUserId = "1";
        String regionId = "1";
        String name = "name";
        String description = "description";
        String subDomain = "www.baidu.com";
        Integer trafficLimit = 100;
        String id = this.groupService.addGroup(loginUserId, regionId, name, description, subDomain, trafficLimit);
        GroupV1 groupV1 = this.groupService.getGroupV1ById("1", id);
        assertNotNull(groupV1);
        this.groupService.delGroupById(loginUserId, id);
        assertNull(this.groupService.getGroupV1ById("1", id));
    }

    @Test
    public void updateGroup() {
        String loginUserId = "1";
        String regionId = "1";
        String name = "name";
        String description = "description";
        String subDomain = "www.baidu.com";
        Integer trafficLimit = 100;
        String id = this.groupService.addGroup(loginUserId, regionId, name, description, subDomain, trafficLimit);
        GroupV1 groupV1 = this.groupService.getGroupV1ById("1", id);
        assertNotNull(groupV1);
        String newName = "newName";
        String newDescription = "newDescription";
        String newSubDomain = "new.baidu.com";
        Integer newTrafficLimit = 200;
        this.groupService.updateGroup(loginUserId, id, newName, newDescription, newSubDomain, newTrafficLimit);
        groupV1 = this.groupService.getGroupV1ById("1", id);
        assertEquals(newName, groupV1.getName());
        assertEquals(newDescription, groupV1.getDescription());
        assertEquals(newSubDomain, groupV1.getSubDomain());
        assertEquals(newTrafficLimit, groupV1.getTrafficLimit());
    }

    @Test
    public void getGroupV1ById() {
        String loginUserId = "2";
        String regionId = "2";
        String name = "name2";
        String description = "description2";
        String subDomain = "2.baidu.com";
        Integer trafficLimit = 100;
        String id = this.groupService.addGroup(loginUserId, regionId, name, description, subDomain, trafficLimit);
        GroupV1 groupV1 = this.groupService.getGroupV1ById("1", id);
        assertNotNull(groupV1);
        assertEquals(id, groupV1.getId());
        assertEquals(regionId, groupV1.getRegionId());
        assertEquals(name, groupV1.getName());
        assertEquals(description, groupV1.getDescription());
        assertEquals(subDomain, groupV1.getSubDomain());
        assertEquals(trafficLimit, groupV1.getTrafficLimit());
    }

    @Test
    public void getPagination() {
        String loginUserId = "2";
        String regionId = "2";
        String name = "name2";
        String description = "description2";
        String subDomain = "2.baidu.com";
        Integer trafficLimit = 100;
        String id = this.groupService.addGroup(loginUserId, regionId, name, description, subDomain, trafficLimit);
        Pagination pagination = this.groupService.getPagination(loginUserId,regionId,"",1,5);
        assertEquals(1,pagination.getTotalCount());
    }
}