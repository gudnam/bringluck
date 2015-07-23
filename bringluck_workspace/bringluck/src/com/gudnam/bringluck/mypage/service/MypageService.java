package com.gudnam.bringluck.mypage.service;

import java.util.List;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gudnam.bringluck.R;
import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.domain.LottoResultVo;

public class MypageService {

	public MypageService(){}
	
	public static void initWinningLotto(TextView tv_lotto_age, TextView tv_winning_year, TextView tv_winning_date, ImageView iv_win_balls[]){
		tv_lotto_age.setText("");
		tv_winning_year.setText("");
		tv_winning_date.setText("");
		for(ImageView iv : iv_win_balls){
			iv.setBackgroundResource(0);
		}
	}
	
	public static void setImageWinningBall(ImageView win_view, String win_num){
		int winNum = Integer.parseInt(win_num);
		if( winNum == 1) win_view.setBackgroundResource(R.drawable.s01);
		else if( winNum == 2) win_view.setBackgroundResource(R.drawable.s02);
		else if( winNum == 3) win_view.setBackgroundResource(R.drawable.s03);
		else if( winNum == 4) win_view.setBackgroundResource(R.drawable.s04);
		else if( winNum == 5) win_view.setBackgroundResource(R.drawable.s05);
		else if( winNum == 6) win_view.setBackgroundResource(R.drawable.s06);
		else if( winNum == 7) win_view.setBackgroundResource(R.drawable.s07);
		else if( winNum == 8) win_view.setBackgroundResource(R.drawable.s08);
		else if( winNum == 9) win_view.setBackgroundResource(R.drawable.s09);
		else if( winNum == 10) win_view.setBackgroundResource(R.drawable.s10);
		else if( winNum == 11) win_view.setBackgroundResource(R.drawable.s11);
		else if( winNum == 12) win_view.setBackgroundResource(R.drawable.s12);
		else if( winNum == 13) win_view.setBackgroundResource(R.drawable.s13);
		else if( winNum == 14) win_view.setBackgroundResource(R.drawable.s14);
		else if( winNum == 15) win_view.setBackgroundResource(R.drawable.s15);
		else if( winNum == 16) win_view.setBackgroundResource(R.drawable.s16);
		else if( winNum == 17) win_view.setBackgroundResource(R.drawable.s17);
		else if( winNum == 18) win_view.setBackgroundResource(R.drawable.s18);
		else if( winNum == 19) win_view.setBackgroundResource(R.drawable.s19);
		else if( winNum == 20) win_view.setBackgroundResource(R.drawable.s20);
		else if( winNum == 21) win_view.setBackgroundResource(R.drawable.s21);
		else if( winNum == 22) win_view.setBackgroundResource(R.drawable.s22);
		else if( winNum == 23) win_view.setBackgroundResource(R.drawable.s23);
		else if( winNum == 24) win_view.setBackgroundResource(R.drawable.s24);
		else if( winNum == 25) win_view.setBackgroundResource(R.drawable.s25);
		else if( winNum == 26) win_view.setBackgroundResource(R.drawable.s26);
		else if( winNum == 27) win_view.setBackgroundResource(R.drawable.s27);
		else if( winNum == 28) win_view.setBackgroundResource(R.drawable.s28);
		else if( winNum == 29) win_view.setBackgroundResource(R.drawable.s29);
		else if( winNum == 30) win_view.setBackgroundResource(R.drawable.s30);
		else if( winNum == 31) win_view.setBackgroundResource(R.drawable.s31);
		else if( winNum == 32) win_view.setBackgroundResource(R.drawable.s32);
		else if( winNum == 33) win_view.setBackgroundResource(R.drawable.s33);
		else if( winNum == 34) win_view.setBackgroundResource(R.drawable.s34);
		else if( winNum == 35) win_view.setBackgroundResource(R.drawable.s35);
		else if( winNum == 36) win_view.setBackgroundResource(R.drawable.s36);
		else if( winNum == 37) win_view.setBackgroundResource(R.drawable.s37);
		else if( winNum == 38) win_view.setBackgroundResource(R.drawable.s38);
		else if( winNum == 39) win_view.setBackgroundResource(R.drawable.s39);
		else if( winNum == 40) win_view.setBackgroundResource(R.drawable.s40);
		else if( winNum == 41) win_view.setBackgroundResource(R.drawable.s41);
		else if( winNum == 42) win_view.setBackgroundResource(R.drawable.s42);
		else if( winNum == 43) win_view.setBackgroundResource(R.drawable.s43);
		else if( winNum == 44) win_view.setBackgroundResource(R.drawable.s44);
		else if( winNum == 45) win_view.setBackgroundResource(R.drawable.s45);
	}
	
	public static void setImageLottoBall(String[] winning_nums, LinearLayout lotto_background, ImageView lotto_view, String lotto_num, int position){
		int lottoNum = Integer.parseInt(lotto_num);
		if( winning_nums != null){
			for(String winningNum : winning_nums){
				int winNum = Integer.parseInt(winningNum);
				if( lottoNum == winNum){
					int backgroundColor = 0;
					if( position == 0){
						backgroundColor = R.drawable.mypage_1y;
					} else if( position == 1){
						backgroundColor = R.drawable.mypage_2y;
					} else if( position == 2){
						backgroundColor = R.drawable.mypage_3y;
					} else if( position == 3){
						backgroundColor = R.drawable.mypage_4y;
					} else if( position == 4){
						backgroundColor = R.drawable.mypage_5y;
					} else if( position == 5){
						backgroundColor = R.drawable.mypage_6y;
					}
					lotto_background.setBackgroundResource(backgroundColor);
				}
			}
		}
		if( lottoNum == 1) lotto_view.setBackgroundResource(R.drawable.b01);
		else if( lottoNum == 2) lotto_view.setBackgroundResource(R.drawable.b02);
		else if( lottoNum == 3) lotto_view.setBackgroundResource(R.drawable.b03);
		else if( lottoNum == 4) lotto_view.setBackgroundResource(R.drawable.b04);
		else if( lottoNum == 5) lotto_view.setBackgroundResource(R.drawable.b05);
		else if( lottoNum == 6) lotto_view.setBackgroundResource(R.drawable.b06);
		else if( lottoNum == 7) lotto_view.setBackgroundResource(R.drawable.b07);
		else if( lottoNum == 8) lotto_view.setBackgroundResource(R.drawable.b08);
		else if( lottoNum == 9) lotto_view.setBackgroundResource(R.drawable.b09);
		else if( lottoNum == 10) lotto_view.setBackgroundResource(R.drawable.b10);
		else if( lottoNum == 11) lotto_view.setBackgroundResource(R.drawable.b11);
		else if( lottoNum == 12) lotto_view.setBackgroundResource(R.drawable.b12);
		else if( lottoNum == 13) lotto_view.setBackgroundResource(R.drawable.b13);
		else if( lottoNum == 14) lotto_view.setBackgroundResource(R.drawable.b14);
		else if( lottoNum == 15) lotto_view.setBackgroundResource(R.drawable.b15);
		else if( lottoNum == 16) lotto_view.setBackgroundResource(R.drawable.b16);
		else if( lottoNum == 17) lotto_view.setBackgroundResource(R.drawable.b17);
		else if( lottoNum == 18) lotto_view.setBackgroundResource(R.drawable.b18);
		else if( lottoNum == 19) lotto_view.setBackgroundResource(R.drawable.b19);
		else if( lottoNum == 20) lotto_view.setBackgroundResource(R.drawable.b20);
		else if( lottoNum == 21) lotto_view.setBackgroundResource(R.drawable.b21);
		else if( lottoNum == 22) lotto_view.setBackgroundResource(R.drawable.b22);
		else if( lottoNum == 23) lotto_view.setBackgroundResource(R.drawable.b23);
		else if( lottoNum == 24) lotto_view.setBackgroundResource(R.drawable.b24);
		else if( lottoNum == 25) lotto_view.setBackgroundResource(R.drawable.b25);
		else if( lottoNum == 26) lotto_view.setBackgroundResource(R.drawable.b26);
		else if( lottoNum == 27) lotto_view.setBackgroundResource(R.drawable.b27);
		else if( lottoNum == 28) lotto_view.setBackgroundResource(R.drawable.b28);
		else if( lottoNum == 29) lotto_view.setBackgroundResource(R.drawable.b29);
		else if( lottoNum == 30) lotto_view.setBackgroundResource(R.drawable.b30);
		else if( lottoNum == 31) lotto_view.setBackgroundResource(R.drawable.b31);
		else if( lottoNum == 32) lotto_view.setBackgroundResource(R.drawable.b32);
		else if( lottoNum == 33) lotto_view.setBackgroundResource(R.drawable.b33);
		else if( lottoNum == 34) lotto_view.setBackgroundResource(R.drawable.b34);
		else if( lottoNum == 35) lotto_view.setBackgroundResource(R.drawable.b35);
		else if( lottoNum == 36) lotto_view.setBackgroundResource(R.drawable.b36);
		else if( lottoNum == 37) lotto_view.setBackgroundResource(R.drawable.b37);
		else if( lottoNum == 38) lotto_view.setBackgroundResource(R.drawable.b38);
		else if( lottoNum == 39) lotto_view.setBackgroundResource(R.drawable.b39);
		else if( lottoNum == 40) lotto_view.setBackgroundResource(R.drawable.b40);
		else if( lottoNum == 41) lotto_view.setBackgroundResource(R.drawable.b41);
		else if( lottoNum == 42) lotto_view.setBackgroundResource(R.drawable.b42);
		else if( lottoNum == 43) lotto_view.setBackgroundResource(R.drawable.b43);
		else if( lottoNum == 44) lotto_view.setBackgroundResource(R.drawable.b44);
		else if( lottoNum == 45) lotto_view.setBackgroundResource(R.drawable.b45);
	}
	
	public static void setImageLottoRank(String text_rank, ImageView img_rank){
		img_rank.setBackgroundResource(0);
		if( text_rank == null){
			return;
		}
		try{
			int rank = Integer.parseInt(text_rank);
			if( rank == BLConfig.LOTTO_THE_FAIL){
				return;
			} else if( rank == BLConfig.LOTTO_THE_FIRST){
				img_rank.setBackgroundResource(R.drawable.pp1);
			} else if( rank == BLConfig.LOTTO_THE_SECOND){
				img_rank.setBackgroundResource(R.drawable.pp2);
			} else if( rank == BLConfig.LOTTO_THE_THIRD){
				img_rank.setBackgroundResource(R.drawable.pp3);
			} else if( rank == BLConfig.LOTTO_THE_FORTH){
				img_rank.setBackgroundResource(R.drawable.pp4);
			} else if( rank == BLConfig.LOTTO_THE_FIFTH){
				img_rank.setBackgroundResource(R.drawable.pp5);
			} else {
				return;
			}
		} catch(NumberFormatException ex){
			ex.printStackTrace();
		} catch( Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void setImageLottoTopRank(List<LottoResultVo> lottoResultVoList, ImageView img_top_rank){
		img_top_rank.setBackgroundResource(0);
		int top_rank = 9;
		for(LottoResultVo lottoResultVo : lottoResultVoList){
			if( lottoResultVo.getRank() == null){
				return;
			}
			try{
				int rank = Integer.parseInt(lottoResultVo.getRank());
				if( rank == BLConfig.LOTTO_THE_FAIL){
					rank = 9;
				}
				if( rank < top_rank ){
					top_rank = rank;
				}
			} catch(NumberFormatException ex){
				ex.printStackTrace();
			} catch( Exception ex){
				ex.printStackTrace();
			}
		}
		if( top_rank > BLConfig.LOTTO_THE_FIFTH || top_rank == BLConfig.LOTTO_THE_FAIL){
			return;
		} else if( top_rank == BLConfig.LOTTO_THE_FIRST){
			img_top_rank.setBackgroundResource(R.drawable.pp1);
		} else if( top_rank == BLConfig.LOTTO_THE_SECOND){
			img_top_rank.setBackgroundResource(R.drawable.pp2);
		} else if( top_rank == BLConfig.LOTTO_THE_THIRD){
			img_top_rank.setBackgroundResource(R.drawable.pp3);
		} else if( top_rank == BLConfig.LOTTO_THE_FORTH){
			img_top_rank.setBackgroundResource(R.drawable.pp4);
		} else if( top_rank == BLConfig.LOTTO_THE_FIFTH){
			img_top_rank.setBackgroundResource(R.drawable.pp5);
		} else {
			return;
		}
		
		return;
	}
}
