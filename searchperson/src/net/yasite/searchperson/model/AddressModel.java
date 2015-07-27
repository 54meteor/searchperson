package net.yasite.searchperson.model;

import java.util.List;

import net.yasite.searchperson.entity.AddressEntitiy;
import net.yasite.searchperson.service.AddressService;
import android.content.Context;

public class AddressModel {
	
	Context context;
	AddressService addressService;
	
	public AddressModel(Context context){
		this.context = context;
		addressService = new AddressService(context);
	}
	
	public long addAddress(AddressEntitiy entity){
		return addressService.addAddress(entity);
	}

	public void updateAddress(AddressEntitiy entity){
		addressService.updateAddress(entity);
	}
	public void delAddress(AddressEntitiy entity){
		addressService.delAddress(entity);
	}
	public List<AddressEntitiy> getAddressList(){
		return addressService.getAddressList();
	}
	public AddressEntitiy getAddressInfo(long id){
		return addressService.getAddressInfo(id);
	}
}
