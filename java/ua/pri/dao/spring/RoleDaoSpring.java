package ua.pri.dao.spring;

import org.springframework.stereotype.Repository;

import ua.pri.dao.RoleDao;
import ua.pri.ent.Role;
@Repository("roleDao")
public class RoleDaoSpring extends AbstractDaoSpring<Role> implements RoleDao {

	@Override
	protected Class<Role> entityClass() {
		// TODO Auto-generated method stub
		return Role.class;
	}

	@Override
	public Role student(){
		return (Role) getSession().get(Role.class, 1);
	}
	public Role admin(){
		return (Role) getSession().get(Role.class, 0);
	}
	public Role tutor(){
		return (Role) getSession().get(Role.class, 2);
	}
	public Role advancedTutor(){
		return (Role) getSession().get(Role.class, 3);
	}

}
