package com.pinyougou.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UserDetailsServiceImpl implements UserDetailsService {


    private SellerService sellerService;

    public UserDetailsServiceImpl() {
    }

    public UserDetailsServiceImpl(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public SellerService getSellerService() {
        return sellerService;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
//        return new User(username,"123",grantedAuthorities);
        TbSeller tbSeller = sellerService.findOne(username);
        if (tbSeller == null || !tbSeller.getStatus().equals("1")) {
//            return new User(username, tbSeller.getPassword(), grantedAuthorities);
            return null;
        }
        return new User(username, tbSeller.getPassword(), grantedAuthorities);
    }
}
