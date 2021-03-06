package com.gudnam.bringluck.mypage.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.domain.LottoResultVo;
import com.gudnam.bringluck.domain.LottoVo;
import com.gudnam.bringluck.domain.UserLottoVo;
import com.gudnam.bringluck.sqlite.BLSQLiteQuery;

public class LottoResultService {
	
	private final static int USER_LOTTO_COUNT = 6;
	private final static int WINNING_LOTTO_COUNT = 7;
	
	/*
	 * 사용자의 로또 결과 : 당첨 번호 비교하여 TB_LOTTO_RESULT 에 저장 후 최고 등수 리턴
	 */
	public static int resultMyLotto(Context context, String lottoAge){
		Log.i("INFO", "[resultMyLotto] start");
		if( context == null){
			return -1;
		}
		LottoVo lottoVo = BLSQLiteQuery.getLottoByLottoAge(lottoAge);
		if( lottoVo == null){
			return -2;
		}
		List<UserLottoVo> userLottoVoList = BLSQLiteQuery.getUserLottoByLottoAge(lottoAge);
		if( userLottoVoList == null){
			return -3;
		}
		int rank = -1;
		
		for(UserLottoVo userLottoVo : userLottoVoList){
			List<Integer> sameLottoNumList = getSameLottoNumber(
													userLottoVo.getLottoNumber(), 
													lottoVo.getWinningNumber());
			int bonusNum = Integer.parseInt(lottoVo.getWinningNumber().split(",")[6]);
			rank = getUserLottoRank(sameLottoNumList, bonusNum);
			saveLottoResult(context, userLottoVo, lottoVo, sameLottoNumList, rank);
		}
		
		Log.i("INFO", "[resultMyLotto] end");
		return rank;
	}
	
	/*
	 * 사용자 로또 번호와 당첨 번호를 비교하여 같은 번호 리스트에 저장하여 리턴
	 */
	private static List<Integer> getSameLottoNumber(String userNum, String winningNum){
		Log.i("INFO", "[getSameLottoNumber] start");
		List<Integer> userNumList = new ArrayList<Integer>();
		List<Integer> winningNumList = new ArrayList<Integer>();
		List<Integer> sameNumList = new ArrayList<Integer>();
		
		try{
			for(int userCnt=0; userCnt<USER_LOTTO_COUNT; userCnt++){
				userNumList.add(Integer.parseInt(userNum.split(",")[userCnt]));
			}
			for(int winCnt=0; winCnt<WINNING_LOTTO_COUNT; winCnt++){
				winningNumList.add(Integer.parseInt(winningNum.split(",")[winCnt]));
			}
		} catch( NumberFormatException ne){
			ne.printStackTrace();
		} catch( Exception ex){
			ex.printStackTrace();
		}
		for(int winNum : winningNumList){
			for(int uNum : userNumList){
				if( uNum == winNum){
					sameNumList.add(uNum);
				}
			}
		}

		Log.i("INFO", "[getSameLottoNumber] end");
		return sameNumList;
	}
	
	private static int getUserLottoRank(List<Integer> sameNumList, int bonusNum){
		Log.i("INFO", "[getUserLottoRank] start");
		
		boolean isBonus = false;
		for(int cnt=0; cnt<sameNumList.size(); cnt++){
			if( sameNumList.get(cnt) == bonusNum){
				sameNumList.remove(cnt);
				isBonus = true;
			}
		}
		
		int rank = BLConfig.LOTTO_THE_FAIL;
		if( sameNumList.size() == 6){
			rank = BLConfig.LOTTO_THE_FIRST;
		} else if( sameNumList.size() == 5){
			if( isBonus){
				rank = BLConfig.LOTTO_THE_SECOND;
			} else {
				rank = BLConfig.LOTTO_THE_THIRD;
			}
		} else if( sameNumList.size() == 4){
			rank = BLConfig.LOTTO_THE_FORTH;
		} else if( sameNumList.size() == 4){
			rank = BLConfig.LOTTO_THE_FORTH;
		} else if( sameNumList.size() == 3){
			rank = BLConfig.LOTTO_THE_FIFTH;
		} else{
			rank = BLConfig.LOTTO_THE_FAIL;
		}

		Log.i("INFO", "[getUserLottoRank] rank : " + rank);
		Log.i("INFO", "[getUserLottoRank] end");
		return rank;
	}
	
	private static void saveLottoResult(Context context, UserLottoVo userLottoVo, LottoVo lottoVo, 
									List<Integer> sameNumList, int rank){
		Log.i("INFO", "[saveLottoResult] start");
		String sameNum = "";
		for(int num : sameNumList){
			if( num < 10){
				sameNum += "0";
			}
			sameNum += num + ",";
		}
		if(sameNumList.size() > 0){
			sameNum = sameNum.substring(0, sameNum.length()-1);
		}
		LottoResultVo lottoResultVo = new LottoResultVo(
										userLottoVo.getUserId(), lottoVo.getLottoAge(), 
										userLottoVo.getLottoNumber(), userLottoVo.getLottoDate(), 
										String.valueOf(rank), lottoVo.getWinningNumber(), 
										lottoVo.getWinningDate(), sameNum);
		BLSQLiteQuery.addLottoResult(lottoResultVo);
		
		Log.i("INFO", "[saveLottoResult] end");
	}
}
