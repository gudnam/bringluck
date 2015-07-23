package com.gudnam.bringluck.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gudnam.bringluck.domain.LottoVo;

public class JsonParserUtil {
	public static List<LottoVo> parsingLottoAll(String json) {
		try {
			json = "{\n\"bringluck\": " + json + "\n}";
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray = new JSONArray(jsonObject.getString("bringluck"));
			
			List<LottoVo> lottoVoList = new ArrayList<LottoVo>();
			for(int arrCnt=0; arrCnt<jsonArray.length(); arrCnt++){
				JSONObject inObject = jsonArray.getJSONObject(arrCnt);
				LottoVo lottoVo = new LottoVo();
				lottoVo.setLottoAge(inObject.getString("lotto_age"));
				lottoVo.setWinningNumber(inObject.getString("winning_number"));
				lottoVo.setWinningDate(inObject.getString("winning_date").substring(0, 10));
				lottoVoList.add(lottoVo);
			}

			return lottoVoList;
		} catch (JSONException ex) {
			return null;
		} catch (Exception ex){
			return null;
		}
	}
}
