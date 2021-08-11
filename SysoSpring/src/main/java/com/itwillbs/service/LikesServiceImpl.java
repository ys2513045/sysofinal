package com.itwillbs.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.ItemDAO;
import com.itwillbs.dao.LikesDAO;
import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.likesVO;
import com.itwillbs.util.TimeGap;

@Service
public class LikesServiceImpl implements LikesService {

	@Inject
	private LikesDAO likesDAO;
	@Inject
	private ItemDAO itemDAO;

	


	@Override
	public List<ItemVO> memberLikesList(String member_id) {
		List<Integer> likesListNum = likesDAO.memberLikesList(member_id);
		
		List<ItemVO> likesList = new ArrayList<ItemVO>();
		for(int i=0; i<likesListNum.size(); i++) {
			likesList.add(itemDAO.getItem(likesListNum.get(i)));
		}
		TimeGap.setItemDateFormat(likesList);
		
		return likesList;
	}



}
