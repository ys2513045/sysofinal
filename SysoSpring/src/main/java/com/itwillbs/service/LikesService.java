package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.ItemVO;

public interface LikesService {


	public List<ItemVO> memberLikesList(String member_id);

	
}
