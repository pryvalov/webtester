package ua.pri.dao.impl;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ua.pri.dao.RoleDao;
import ua.pri.ent.Role;
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {

	@Override
	protected Class<Role> entityClass() {
		// TODO Auto-generated method stub
		return Role.class;
	}
	/* (non-Javadoc)
	 * @see sourceit.dao.impl.RoleDaoInterface#student()
	 */
	@Override
	public Role student(){
		return (Role)select(new Callback() {
			@Override
			public Object invoke(Session s) {
				Criteria c = s.createCriteria(entityClass());
				c.add(Restrictions.idEq(1));
				return c.uniqueResult();
			}
		});
	}

}
