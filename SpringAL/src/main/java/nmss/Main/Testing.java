package nmss.Main;

import com.google.gson.Gson;

import nmss.pojos.crbt.client.BalanceResponse;

public class Testing {

	public static void main(String[] args) {
		String h = "{\"return_description\":\"\",\"return_result\":[{\"tx_banks\":[{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_archive_balance\":0,\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260337305640,\"balance\":320.72,\"bank_name\":\"MAIN_BZD\",\"credit_limit\":1.0E8,\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_event\":0,\"external_tx_balance\":-100,\"last_archive_date\":\"\",\"bank_type\":2,\"invoiced_ind\":true,\"source_type\":\"1\",\"OID\":\"0:514:1260429219330\",\"usage_orientation\":1,\"main_bank_ind\":true,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_archive_balance\":0,\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260337995599,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Data\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"2\",\"OID\":\"0:530:1260432857368\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_archive_balance\":0,\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260434094225,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Data_MiTV\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"2\",\"OID\":\"0:530:1260436347767\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_archive_balance\":0,\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260340117141,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Data_Pass_Facebook\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"2\",\"OID\":\"0:530:1260432857374\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_archive_balance\":0,\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260337315806,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Data_Promo\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"2\",\"OID\":\"0:530:1260432857366\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_archive_balance\":0,\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260340058541,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Data_Roaming\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"2\",\"OID\":\"0:530:1260432857372\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_archive_balance\":0,\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260341347398,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Data_Share\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"2\",\"OID\":\"0:530:1260432857378\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_archive_balance\":0,\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260337995972,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Data_Social\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"2\",\"OID\":\"0:530:1260432857370\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_archive_balance\":0,\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260336871001,\"balance\":-495,\"bank_name\":\"DepositBank\",\"credit_limit\":0,\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":2,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:514:1260429219442\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_archive_balance\":0,\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260337315802,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":-88,\"bank_name\":\"Prepaid_Credit\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:530:1260432857360\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_archive_balance\":0,\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260337915594,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Promo_Credit\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:530:1260432857356\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"SMS\",\"last_archive_balance\":0,\"precision\":\"0\",\"write_tx_ind\":true,\"attribute_obj_num\":1260335821050,\"bank_obj_num\":1260340639487,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"SMS_Bundle\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"SMS\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:530:1260432857413\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"SMS\",\"last_archive_balance\":0,\"precision\":\"0\",\"write_tx_ind\":true,\"attribute_obj_num\":1260335821050,\"bank_obj_num\":1260337997306,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"SMS_Promo\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"SMS\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:530:1260432857409\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"SMS\",\"last_archive_balance\":0,\"precision\":\"0\",\"write_tx_ind\":true,\"attribute_obj_num\":1260335821050,\"bank_obj_num\":1260340057841,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"SMS_Roaming\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"SMS\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:530:1260432857411\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_archive_balance\":0,\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260340327102,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"SOS_Loan\",\"credit_limit\":1.0E8,\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:530:1260432857401\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"Min\",\"last_archive_balance\":0,\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337355598,\"bank_obj_num\":1260340667107,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Voice_National\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"Minutes\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:530:1260432857427\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"Min\",\"last_archive_balance\":0,\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337355598,\"bank_obj_num\":1260339898079,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Voice_Promo\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"Minutes\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:530:1260432857423\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"Min\",\"last_archive_balance\":0,\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337355598,\"bank_obj_num\":1260340058213,\"subscriber_obj_num\":1260432857343,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Voice_Roaming\",\"credit_limit\":0,\"balance_tier_type\":1,\"attribute_name\":\"Minutes\",\"part_id\":\"-1\",\"balance_tier_ind\":true,\"next_event\":0,\"external_tx_balance\":0,\"last_archive_date\":\"\",\"bank_type\":4,\"invoiced_ind\":false,\"source_type\":\"1\",\"OID\":\"0:530:1260432857425\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"}]},{\"cyclic_banks\":[{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_balance\":0,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260341128172,\"auto_accumulate_ind\":false,\"balance\":0,\"bank_name\":\"Postpaid_Credit_Owner\",\"credit_limit\":0,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":1,\"source_type\":\"1\",\"OID\":\"0:524:1260436347841\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"0_Postpaid_Credit_Owner\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_balance\":0,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260342639014,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Data_Bundle_Cyclic\",\"credit_limit\":0,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"2\",\"OID\":\"0:525:1260432857380\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343Data_Bundle_Cyclic\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_balance\":-8192,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260340729834,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":-8163.46,\"bank_name\":\"Data_Cyclic\",\"credit_limit\":0,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"2\",\"OID\":\"0:525:1260432857376\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343Data_Cyclic\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_balance\":0,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260343957648,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0.72,\"bank_name\":\"Excess_Credit\",\"credit_limit\":120,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"1\",\"OID\":\"0:525:1260432857382\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343Excess_Credit\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_balance\":-200,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260337665873,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":-200,\"bank_name\":\"EZ_Trans\",\"credit_limit\":0,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"1\",\"OID\":\"0:525:1260432857399\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343EZ_Trans\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_balance\":0,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260343507482,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"International_Credit\",\"credit_limit\":0,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"1\",\"OID\":\"0:525:1260432857392\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343International_Credit\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"MB\",\"last_balance\":0,\"last_balance_date\":\"2000-01-01T00:00:00\",\"precision\":\"6\",\"write_tx_ind\":true,\"attribute_obj_num\":1260336791002,\"bank_obj_num\":1260340117137,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Internet_Pass_Data_Accum\",\"credit_limit\":1.0E8,\"exp_date\":\"2017-12-03T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"MB\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"\",\"next_event\":1260340117147,\"bank_type\":3,\"source_type\":\"2\",\"OID\":\"0:525:1260433311182\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":1024,\"srt\":\"1260432857343Internet_Pass_Data_Accum\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_balance\":-190.99,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260340827680,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":-117.12,\"bank_name\":\"Postpaid_Credit\",\"credit_limit\":0,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"1\",\"OID\":\"0:525:1260432857362\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343Postpaid_Credit\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_balance\":0,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260343957866,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Roaming_Credit\",\"credit_limit\":0,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"1\",\"OID\":\"0:525:1260432857384\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343Roaming_Credit\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"SMS\",\"last_balance\":-75,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"0\",\"write_tx_ind\":true,\"attribute_obj_num\":1260335821050,\"bank_obj_num\":1260340837091,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"SMS_Cyclic\",\"credit_limit\":0,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"SMS\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"1\",\"OID\":\"0:525:1260432857403\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343SMS_Cyclic\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"DEF\",\"last_balance\":0,\"last_balance_date\":\"2017-11-03T00:00:00\",\"precision\":\"0\",\"write_tx_ind\":false,\"attribute_obj_num\":114,\"bank_obj_num\":1260340327118,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"SOS_Please_Call\",\"credit_limit\":5,\"exp_date\":\"2017-12-03T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"amount\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"2\",\"OID\":\"0:525:1260432857419\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343SOS_Please_Call\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"DEF\",\"last_balance\":0,\"last_balance_date\":\"2017-11-03T00:00:00\",\"precision\":\"0\",\"write_tx_ind\":false,\"attribute_obj_num\":114,\"bank_obj_num\":1260340327110,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"SOS_Please_Recharge\",\"credit_limit\":5,\"exp_date\":\"2017-12-03T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"amount\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"2\",\"OID\":\"0:525:1260432857417\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343SOS_Please_Recharge\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":false,\"currency_ind\":false,\"unit_sign\":\"Min\",\"last_balance\":0,\"last_balance_date\":\"2017-12-02T00:00:00\",\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337355598,\"bank_obj_num\":1260340969108,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Voice_Cyclic\",\"credit_limit\":0,\"exp_date\":\"2018-01-02T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"Minutes\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"2018-01-02T00:00:00\",\"next_event\":0,\"bank_type\":3,\"source_type\":\"1\",\"OID\":\"0:525:1260432857429\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":0,\"srt\":\"1260432857343Voice_Cyclic\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2017-12-02T00:00:00\",\"next_cycle_date\":\"2018-01-02T00:00:00\"},{\"last_cycle_open_ind\":true,\"currency_ind\":true,\"unit_sign\":\"BZD\",\"last_balance\":0,\"last_balance_date\":\"2017-12-03T00:00:00\",\"precision\":\"2\",\"write_tx_ind\":true,\"attribute_obj_num\":1260337305636,\"bank_obj_num\":1260435779326,\"subscriber_obj_num\":1260432857343,\"auto_accumulate_ind\":false,\"subscriber_id\":\"1260432857343\",\"balance\":0,\"bank_name\":\"Xmas_Voice_Usage_Accum\",\"credit_limit\":1.0E8,\"exp_date\":\"2017-12-06T00:00:00\",\"balance_tier_type\":0,\"attribute_name\":\"BZD\",\"part_id\":\"-1\",\"balance_tier_ind\":false,\"next_balance_date\":\"\",\"next_event\":1260435779300,\"bank_type\":3,\"source_type\":\"1\",\"OID\":\"0:525:1260436347771\",\"usage_orientation\":1,\"main_bank_ind\":false,\"next_event_balance\":20,\"srt\":\"1260432857343Xmas_Voice_Usage_Accum\",\"customer_obj_num\":1260429219325,\"last_cycle_date\":\"2018-01-02T00:00:00\",\"next_cycle_date\":\"2018-02-02T00:00:00\"}]}],\"return_message\":\"\",\"return_code\":0}";
		BalanceResponse balanceResponse = new Gson().fromJson(h, BalanceResponse.class);
		System.out.println(balanceResponse);
	}

}
