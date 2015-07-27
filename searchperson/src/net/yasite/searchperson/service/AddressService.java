package net.yasite.searchperson.service;

import java.util.List;

import net.yasite.searchperson.dao.AddressDao;
import net.yasite.searchperson.entity.AddressEntitiy;
import net.yasite.util.BaseService;
import android.content.Context;

public class AddressService extends BaseService {
	AddressDao dao;
	public AddressService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		dao = (AddressDao)getDao(AddressEntitiy.class);
	}
	
	public long addAddress(AddressEntitiy entity){
		return dao.insert(entity);
	}
	
	public void updateAddress(AddressEntitiy entity){
		dao.update(entity);
	}
	
	public void delAddress(AddressEntitiy entity){
		dao.delete(entity);
	}
	
	public List<AddressEntitiy> getAddressList(){
		return dao.queryBuilder()
				.orderAsc(AddressDao.Properties.Pinyin).list();
	}

	public AddressEntitiy getAddressInfo(long id) {
		// TODO Auto-generated method stub
		return dao.queryBuilder().where(AddressDao.Properties._id.eq(id)).unique();
	}

}
