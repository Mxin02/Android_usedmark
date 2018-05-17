package com.example.myapplication.base;

import java.util.List;

public class JuHeBean {

	/**
	 * reason : �ɹ��ķ��� result :
	 * {"stat":"1","data":[{"uniquekey":"4493800c45fd126f8653244e9fa65bc2"
	 * ,"title"
	 * :"����׹��ȴл������ԱӪ�� ԭ�ǾƼݺ�������ܴ���","date":"2017-10-07 13:06","category":"ͷ��"
	 * ,"author_name"
	 * :"��������","url":"http://mini.eastday.com/mobile/171007130632169.html"
	 * ,"thumbnail_pic_s":
	 * "http://05.imgmini.eastday.com/mobile/20171007/20171007130632_29a34c5a273dd40a3e7d81774334a4af_2_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://05.imgmini.eastday.com/mobile/20171007/20171007130632_29a34c5a273dd40a3e7d81774334a4af_3_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://05.imgmini.eastday.com/mobile/20171007/20171007130632_29a34c5a273dd40a3e7d81774334a4af_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"93368a5dcad2e0ffbe91f24d8e46d099","title":
	 * "�����Գ����������ô�����Ǳ�׼�ģ������˻��ܻ�ʽŰ����"
	 * ,"date":"2017-10-07 12:51","category":"ͷ��","author_name"
	 * :"�������ing","url":"http://mini.eastday.com/mobile/171007125120615.html"
	 * ,"thumbnail_pic_s":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007125120_a0b10286871e393fe75b954af4e1e4a2_5_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007125120_a0b10286871e393fe75b954af4e1e4a2_3_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007125120_a0b10286871e393fe75b954af4e1e4a2_2_mwpm_03200403.jpg"
	 * },{"uniquekey":"6d8851c8cf02c01e8ad1685a1e84b007","title":
	 * "�ȸ���˹�����Ͷ�ʳ��ֻر� ��ע�й�AI�����"
	 * ,"date":"2017-10-07 12:29","category":"ͷ��","author_name"
	 * :"���˲ƾ�","url":"http://mini.eastday.com/mobile/171007122959094.html"
	 * ,"thumbnail_pic_s":
	 * "http://05.imgmini.eastday.com/mobile/20171007/20171007122959_45c0bf16f190bc1c6e0f9721ef8d1446_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://05.imgmini.eastday.com/mobile/20171007/20171007122959_45c0bf16f190bc1c6e0f9721ef8d1446_2_mwpm_03200403.jpg"
	 * },{"uniquekey":"3c24af288ce8622c6eb29c9ba1c966a9","title":
	 * "����ɯ��÷����30����Ա�����ְ:�ڸ�ȫ��ͦ��"
	 * ,"date":"2017-10-07 12:24","category":"ͷ��","author_name"
	 * :"������","url":"http://mini.eastday.com/mobile/171007122407503.html"
	 * ,"thumbnail_pic_s":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007122407_4695822cd7a3cf9eb5456bd03179bb61_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"1e70fbda37f59b596aab1be367977214","title":
	 * "������� ���ݴ��ٲ����ֺ���˫�ʺ�"
	 * ,"date":"2017-10-07 12:06","category":"ͷ��","author_name"
	 * :"��������㲥����̨","url"
	 * :"http://mini.eastday.com/mobile/171007120602817.html","thumbnail_pic_s":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007120602_e696e392924f8addfe46963c4356be53_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007120602_e696e392924f8addfe46963c4356be53_3_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007120602_e696e392924f8addfe46963c4356be53_2_mwpm_03200403.jpg"
	 * },{"uniquekey":"15c455a918f59ba832811b98a00d2d37","title":
	 * "1Ԫ����������� ů���ο͵����Ӹ�ů������"
	 * ,"date":"2017-10-07 11:58","category":"ͷ��","author_name"
	 * :"�й���","url":"http://mini.eastday.com/mobile/171007115841299.html"
	 * ,"thumbnail_pic_s":
	 * "http://06.imgmini.eastday.com/mobile/20171007/20171007115841_9221eee1eacebdbeff229be25c92b24c_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"6415c671198691965fcf56a30dfd6280","title":
	 * "�����������ؼ��������ƽ�С�󹩸���ṹ�ĸ�"
	 * ,"date":"2017-10-07 11:55","category":"ͷ��","author_name"
	 * :"����ͷ��","url":"http://mini.eastday.com/mobile/171007115501916.html"
	 * ,"thumbnail_pic_s":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007115501_c648c5624f3d3268ec960a90a9dc6d93_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"5f34ba27f46d5567a9b62da4a3914c45","title":
	 * "��������˽���ʷ����ȫ\u201c��������\u201d �������������ѻ���"
	 * ,"date":"2017-10-07 11:45","category":"ͷ��","author_name":"����ĻῪ��","url":
	 * "http://mini.eastday.com/mobile/171007114522237.html","thumbnail_pic_s":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114522_b8403effc5cb6354428254069d03f146_2_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114522_b8403effc5cb6354428254069d03f146_5_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114522_b8403effc5cb6354428254069d03f146_4_mwpm_03200403.jpg"
	 * },{"uniquekey":"4550f339de305207aa133e31a93fdb91","title":
	 * "��Ϊ�ֻ��������ر��ſἼ�� ����֪���ļ�ֱ����Ϊ�ˣ�"
	 * ,"date":"2017-10-07 11:40","category":"ͷ��","author_name"
	 * :"��ȶ���","url":"http://mini.eastday.com/mobile/171007114037513.html"
	 * ,"thumbnail_pic_s":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114037_e5b229bb8ea690a6694ec376c9740b88_4_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114037_e5b229bb8ea690a6694ec376c9740b88_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114037_e5b229bb8ea690a6694ec376c9740b88_2_mwpm_03200403.jpg"
	 * },{"uniquekey":"69f7b250b02ad8b2036e7280a08735c8","title":
	 * "�����������⹥�� �����ҷ�������ͦ�������ɾ����"
	 * ,"date":"2017-10-07 11:38","category":"ͷ��","author_name"
	 * :"�����","url":"http://mini.eastday.com/mobile/171007113835851.html"
	 * ,"thumbnail_pic_s":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007113835_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007113835_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg"
	 * },{"uniquekey":"aa3f25483fc2d91910f235fc4c5b188e","title":
	 * "�й�װ����ǿ���ڣ�ֱ���˵�����ظ�ԭ������ĳ���ò������"
	 * ,"date":"2017-10-07 11:35","category":"ͷ��","author_name"
	 * :"�˻�����","url":"http://mini.eastday.com/mobile/171007113519526.html"
	 * ,"thumbnail_pic_s":
	 * "http://01.imgmini.eastday.com/mobile/20171007/20171007_65f13f5b1d7ca005b190b25e69838031_cover_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://01.imgmini.eastday.com/mobile/20171007/20171007_22fe611fe8f9605d89d42cd63060ee1d_cover_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://01.imgmini.eastday.com/mobile/20171007/20171007_a6f3e5be81fa2c710ff36226095e91e6_cover_mwpm_03200403.jpg"
	 * },{"uniquekey":"abda5f029cfb76f348dde8d224daecb5","title":"���쳤�ٷ��̸߷�������Ļ",
	 * "date":"2017-10-07 11:28","category":"ͷ��","author_name":"�й�������","url":
	 * "http://mini.eastday.com/mobile/171007112859305.html","thumbnail_pic_s":
	 * "http://07.imgmini.eastday.com/mobile/20171007/20171007112859_603c0fee7e12a4b4ca00464d427fda63_2_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://07.imgmini.eastday.com/mobile/20171007/20171007112859_603c0fee7e12a4b4ca00464d427fda63_5_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://07.imgmini.eastday.com/mobile/20171007/20171007112859_603c0fee7e12a4b4ca00464d427fda63_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"d68f822ab86ceac58979f29a044f591b","title":
	 * "��10�ֶ����Ƿ��޹�ͬ�Ʋ�����֪����"
	 * ,"date":"2017-10-07 11:24","category":"ͷ��","author_name"
	 * :"��ʦ˵","url":"http://mini.eastday.com/mobile/171007112412479.html"
	 * ,"thumbnail_pic_s":
	 * "http://03.imgmini.eastday.com/mobile/20171007/20171007112412_cbc19ec7d8fb35ce3a633dc44b1cf047_3_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://03.imgmini.eastday.com/mobile/20171007/20171007112412_cbc19ec7d8fb35ce3a633dc44b1cf047_2_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://03.imgmini.eastday.com/mobile/20171007/20171007112412_cbc19ec7d8fb35ce3a633dc44b1cf047_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"2a51d2eb95272a786d1908169a6a13eb","title":
	 * "��3����Ф���������ޣ����ĺ����Ǻÿ��ֺ�������Ϣ����С"
	 * ,"date":"2017-10-07 11:19","category":"ͷ��","author_name"
	 * :"��Ф��������","url":"http://mini.eastday.com/mobile/171007111922621.html"
	 * ,"thumbnail_pic_s":
	 * "http://06.imgmini.eastday.com/mobile/20171007/20171007111922_cacd45f4a6fb85305b65aa3c99bbac01_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://06.imgmini.eastday.com/mobile/20171007/20171007111922_cacd45f4a6fb85305b65aa3c99bbac01_2_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://06.imgmini.eastday.com/mobile/20171007/20171007111922_cacd45f4a6fb85305b65aa3c99bbac01_3_mwpm_03200403.jpg"
	 * },{"uniquekey":"5db5234bd60598ab027b36f4cf1d602e","title":
	 * "���Ϲ�ȷ�������Ŀ�� �����й���ǰʮ��ʵ��"
	 * ,"date":"2017-10-07 11:15","category":"ͷ��","author_name"
	 * :"������","url":"http://mini.eastday.com/mobile/171007111516316.html"
	 * ,"thumbnail_pic_s":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007111516_ced37bc2bba49d00a38ac0ea1beedd6b_10_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007111516_ced37bc2bba49d00a38ac0ea1beedd6b_2_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007111516_ced37bc2bba49d00a38ac0ea1beedd6b_6_mwpm_03200403.jpg"
	 * },{"uniquekey":"eb543e652e001388eb59a76e18bae99a","title":
	 * "�����ڣ�����˹8���ط��쵼����ְ"
	 * ,"date":"2017-10-07 11:13","category":"ͷ��","author_name"
	 * :"�»���","url":"http://mini.eastday.com/mobile/171007111355358.html"
	 * ,"thumbnail_pic_s":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007111355_85fd06a4ba9a0c128c3cd6473cbd0f28_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"9c47c82c3e442e6780c7a7a64448bc79","title":
	 * "��Щʳ�ﺬ�д��������棬�ܶ���ȴ�ԵĽ����ζ���Ͻ����ˣ�"
	 * ,"date":"2017-10-07 11:04","category":"ͷ��","author_name"
	 * :"�����ô","url":"http://mini.eastday.com/mobile/171007110438668.html"
	 * ,"thumbnail_pic_s":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007110438_9a18ac2b08186278c87866b8ba2ca428_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007110438_9a18ac2b08186278c87866b8ba2ca428_3_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://08.imgmini.eastday.com/mobile/20171007/20171007110438_9a18ac2b08186278c87866b8ba2ca428_4_mwpm_03200403.jpg"
	 * },{"uniquekey":"48480edb7a544708a05a2e9cd6ee3e07","title":
	 * "������У�ſ��������20��Ǯһ�̣����ϴ��������¶�����룡"
	 * ,"date":"2017-10-07 11:01","category":"ͷ��","author_name":"�Ի��ӳ�","url":
	 * "http://mini.eastday.com/mobile/171007110152255.html","thumbnail_pic_s":
	 * "http://09.imgmini.eastday.com/mobile/20171007/20171007110152_7b5301f017cf3f5d7a8c9be249a7e419_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://09.imgmini.eastday.com/mobile/20171007/20171007110152_7b5301f017cf3f5d7a8c9be249a7e419_4_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://09.imgmini.eastday.com/mobile/20171007/20171007110152_7b5301f017cf3f5d7a8c9be249a7e419_3_mwpm_03200403.jpg"
	 * },{"uniquekey":"a65405a0984c1a38cda4dff12c86ef1a","title":
	 * "\u200b���Ͻ����й���������һ���ִ���ս����ר�ҵĻ������Ŀ����"
	 * ,"date":"2017-10-07 10:57","category":"ͷ��","author_name":"ȫ�۹�����","url":
	 * "http://mini.eastday.com/mobile/171007105755058.html","thumbnail_pic_s":
	 * "http://07.imgmini.eastday.com/mobile/20171007/20171007_9a74ec87118b7d72a60b7506b00401fc_cover_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://07.imgmini.eastday.com/mobile/20171007/20171007_b1c1b959864aa36779b04bb23cc73db7_cover_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://07.imgmini.eastday.com/mobile/20171007/20171007_bb26080dc52ababa4d9ff2bcb785156d_cover_mwpm_03200403.jpg"
	 * },{"uniquekey":"35efbc5f40f787616647e8d58d51a918","title":
	 * "����������ɽ���ձ�Ů������ʮ��֪��������˭���������ҹ������"
	 * ,"date":"2017-10-07 10:55","category":"ͷ��","author_name":"��ʷ��ɭ","url":
	 * "http://mini.eastday.com/mobile/171007105551620.html","thumbnail_pic_s":
	 * "http://01.imgmini.eastday.com/mobile/20171007/20171007105551_db11de4956a7a9863752dd7e14b239d1_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://01.imgmini.eastday.com/mobile/20171007/20171007105551_db11de4956a7a9863752dd7e14b239d1_5_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://01.imgmini.eastday.com/mobile/20171007/20171007105551_db11de4956a7a9863752dd7e14b239d1_3_mwpm_03200403.jpg"
	 * },{"uniquekey":"93524993dfa35151fae749efc6fe18c6","title":
	 * "���⿪�ż����»��� ������Ӯ�����»���"
	 * ,"date":"2017-10-07 10:53","category":"ͷ��","author_name"
	 * :"������","url":"http://mini.eastday.com/mobile/171007105349780.html"
	 * ,"thumbnail_pic_s":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007105349_3379bcf00504bacc829a56ca1ac3eaab_2_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007105349_3379bcf00504bacc829a56ca1ac3eaab_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"3e1dfbc901e7fcbcc668e5ad51a3f66b","title":
	 * "ר�ҳ���ô��ȥ����ᱻ�Ͽ壡�й�����̫���ˣ�������˹���к�"
	 * ,"date":"2017-10-07 10:48","category":"ͷ��","author_name":"����ͼ���","url":
	 * "http://mini.eastday.com/mobile/171007104829831.html","thumbnail_pic_s":
	 * "http://06.imgmini.eastday.com/mobile/20171007/20171007_d8a3e47ea8b2020fbfb28671f8429165_cover_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://06.imgmini.eastday.com/mobile/20171007/20171007_5d59af4f99450f3cb794d0b01c020bc9_cover_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://06.imgmini.eastday.com/mobile/20171007/20171007_08df451926949c6bf69e4438631b5d5d_cover_mwpm_03200403.jpg"
	 * },{"uniquekey":"a6717cecadbdf202880ce8c1e09e3fc5","title":
	 * "Ů�����񻹹��������£����Ѽ��������ܹܣ�"
	 * ,"date":"2017-10-07 10:45","category":"ͷ��","author_name"
	 * :"���ִ���","url":"http://mini.eastday.com/mobile/171007104535744.html"
	 * ,"thumbnail_pic_s":
	 * "http://04.imgmini.eastday.com/mobile/20171007/20171007104535_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://04.imgmini.eastday.com/mobile/20171007/20171007104535_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://04.imgmini.eastday.com/mobile/20171007/20171007104535_d41d8cd98f00b204e9800998ecf8427e_5_mwpm_03200403.jpg"
	 * },{"uniquekey":"14f05c508f0435d379310cdff2b330b5","title":
	 * "ʮһ���Ӿ���ץ��1812��ռ����\u201c����ͨ��\u201dΥ��"
	 * ,"date":"2017-10-07 10:38","category":"ͷ��","author_name":"�Ϲ�����","url":
	 * "http://mini.eastday.com/mobile/171007103841926.html","thumbnail_pic_s":
	 * "http://06.imgmini.eastday.com/mobile/20171007/20171007103841_702f8609b4003fe647dd5b97cbe8f824_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"9159d12c7cff9b9035db084a2313f177","title":
	 * "��֪ʶح������ʲô�磿ʱ��350����ĵ���250����2��"
	 * ,"date":"2017-10-07 10:38","category":"ͷ��","author_name"
	 * :"΢�Ź��ں�\u201c����ͷ��\u201d"
	 * ,"url":"http://mini.eastday.com/mobile/171007103812510.html"
	 * ,"thumbnail_pic_s":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007103812_c2059749ba77af0f1e9f7d73f9f55e0c_4_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007103812_c2059749ba77af0f1e9f7d73f9f55e0c_1_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007103812_c2059749ba77af0f1e9f7d73f9f55e0c_5_mwpm_03200403.jpg"
	 * },{"uniquekey":"4cc1664d4b6a3dc76df527f9a22b7714","title":
	 * "Ӣ��������ɫ�����ֻ�ʯ�����泾��"
	 * ,"date":"2017-10-07 10:37","category":"ͷ��","author_name"
	 * :"�»���","url":"http://mini.eastday.com/mobile/171007103725196.html"
	 * ,"thumbnail_pic_s":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007103725_84b21ece3511f8e5b149c929879df841_3_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007103725_84b21ece3511f8e5b149c929879df841_2_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://00.imgmini.eastday.com/mobile/20171007/20171007103725_84b21ece3511f8e5b149c929879df841_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"eb8e16fc9a7eb26e26212ff0dd6fd0ff","title":
	 * "ӡ��ƶ�����벻����ǿ���������Ҫ�ϳ�����Ǹ��ģ�"
	 * ,"date":"2017-10-07 10:34","category":"ͷ��","author_name"
	 * :"����������","url":"http://mini.eastday.com/mobile/171007103434319.html"
	 * ,"thumbnail_pic_s":
	 * "http://07.imgmini.eastday.com/mobile/20171007/20171007103434_5bfe5ccefcf0f742fd25d5ff09e84e09_5_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s02":
	 * "http://07.imgmini.eastday.com/mobile/20171007/20171007103434_5bfe5ccefcf0f742fd25d5ff09e84e09_2_mwpm_03200403.jpg"
	 * ,"thumbnail_pic_s03":
	 * "http://07.imgmini.eastday.com/mobile/20171007/20171007103434_5bfe5ccefcf0f742fd25d5ff09e84e09_4_mwpm_03200403.jpg"
	 * },{"uniquekey":"1e22470dc05bd4d9e3f7aef784e53811","title":
	 * "�й����г���һ�򣺹����������� ������ɱ����"
	 * ,"date":"2017-10-07 10:16","category":"ͷ��","author_name"
	 * :"�ƾ������ܿ�","url":"http://mini.eastday.com/mobile/171007101656225.html"
	 * ,"thumbnail_pic_s":
	 * "http://02.imgmini.eastday.com/mobile/20171007/20171007101656_9bedcb8cffc33a5a637d33d4630ffec5_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"f361a05b5bc9eea863c315867f1eb01b","title":
	 * "�й���ѧ��ʿ���ӱ߾�����ʧ�� ��ʽ����������"
	 * ,"date":"2017-10-07 10:12","category":"ͷ��","author_name"
	 * :"�����ձ������-������","url"
	 * :"http://mini.eastday.com/mobile/171007101206331.html","thumbnail_pic_s":
	 * "http://06.imgmini.eastday.com/mobile/20171007/20171007101206_44254a89ff826efb097e290a507ba4dc_1_mwpm_03200403.jpg"
	 * },{"uniquekey":"e3ca4572333f697668fc611b69ca1bea","title":
	 * "������ӭ�Լ��η��̸߷塡Ԥ��15:30-20:00���"
	 * ,"date":"2017-10-07 10:08","category":"ͷ��","author_name"
	 * :"������-�人��","url":
	 * "http://mini.eastday.com/mobile/171007100843827.html","thumbnail_pic_s":
	 * "http://01.imgmini.eastday.com/mobile/20171007/20171007100843_30f441bb06659d3b39f18689de84fc1f_1_mwpm_03200403.jpg"
	 * }]} error_code : 0
	 */

	private String reason;
	private ResultBean result;
	private int error_code;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ResultBean getResult() {
		return result;
	}

	public void setResult(ResultBean result) {
		this.result = result;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public static class ResultBean {
		/**
		 * stat : 1 data :
		 * [{"uniquekey":"4493800c45fd126f8653244e9fa65bc2","title"
		 * :"����׹��ȴл������ԱӪ�� ԭ�ǾƼݺ�������ܴ���"
		 * ,"date":"2017-10-07 13:06","category":"ͷ��",
		 * "author_name":"��������","url":
		 * "http://mini.eastday.com/mobile/171007130632169.html"
		 * ,"thumbnail_pic_s":
		 * "http://05.imgmini.eastday.com/mobile/20171007/20171007130632_29a34c5a273dd40a3e7d81774334a4af_2_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://05.imgmini.eastday.com/mobile/20171007/20171007130632_29a34c5a273dd40a3e7d81774334a4af_3_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://05.imgmini.eastday.com/mobile/20171007/20171007130632_29a34c5a273dd40a3e7d81774334a4af_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"93368a5dcad2e0ffbe91f24d8e46d099","title":
		 * "�����Գ����������ô�����Ǳ�׼�ģ������˻��ܻ�ʽŰ����"
		 * ,"date":"2017-10-07 12:51","category":"ͷ��"
		 * ,"author_name":"�������ing","url"
		 * :"http://mini.eastday.com/mobile/171007125120615.html"
		 * ,"thumbnail_pic_s":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007125120_a0b10286871e393fe75b954af4e1e4a2_5_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007125120_a0b10286871e393fe75b954af4e1e4a2_3_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007125120_a0b10286871e393fe75b954af4e1e4a2_2_mwpm_03200403.jpg"
		 * },{"uniquekey":"6d8851c8cf02c01e8ad1685a1e84b007","title":
		 * "�ȸ���˹�����Ͷ�ʳ��ֻر� ��ע�й�AI�����"
		 * ,"date":"2017-10-07 12:29","category":"ͷ��","author_name"
		 * :"���˲ƾ�","url":"http://mini.eastday.com/mobile/171007122959094.html",
		 * "thumbnail_pic_s":
		 * "http://05.imgmini.eastday.com/mobile/20171007/20171007122959_45c0bf16f190bc1c6e0f9721ef8d1446_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://05.imgmini.eastday.com/mobile/20171007/20171007122959_45c0bf16f190bc1c6e0f9721ef8d1446_2_mwpm_03200403.jpg"
		 * },{"uniquekey":"3c24af288ce8622c6eb29c9ba1c966a9","title":
		 * "����ɯ��÷����30����Ա�����ְ:�ڸ�ȫ��ͦ��"
		 * ,"date":"2017-10-07 12:24","category":"ͷ��","author_name"
		 * :"������","url":"http://mini.eastday.com/mobile/171007122407503.html"
		 * ,"thumbnail_pic_s":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007122407_4695822cd7a3cf9eb5456bd03179bb61_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"1e70fbda37f59b596aab1be367977214","title":
		 * "������� ���ݴ��ٲ����ֺ���˫�ʺ�"
		 * ,"date":"2017-10-07 12:06","category":"ͷ��","author_name"
		 * :"��������㲥����̨","url"
		 * :"http://mini.eastday.com/mobile/171007120602817.html"
		 * ,"thumbnail_pic_s":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007120602_e696e392924f8addfe46963c4356be53_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007120602_e696e392924f8addfe46963c4356be53_3_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007120602_e696e392924f8addfe46963c4356be53_2_mwpm_03200403.jpg"
		 * },{"uniquekey":"15c455a918f59ba832811b98a00d2d37","title":
		 * "1Ԫ����������� ů���ο͵����Ӹ�ů������"
		 * ,"date":"2017-10-07 11:58","category":"ͷ��","author_name"
		 * :"�й���","url":"http://mini.eastday.com/mobile/171007115841299.html"
		 * ,"thumbnail_pic_s":
		 * "http://06.imgmini.eastday.com/mobile/20171007/20171007115841_9221eee1eacebdbeff229be25c92b24c_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"6415c671198691965fcf56a30dfd6280","title":
		 * "�����������ؼ��������ƽ�С�󹩸���ṹ�ĸ�"
		 * ,"date":"2017-10-07 11:55","category":"ͷ��","author_name"
		 * :"����ͷ��","url":"http://mini.eastday.com/mobile/171007115501916.html",
		 * "thumbnail_pic_s":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007115501_c648c5624f3d3268ec960a90a9dc6d93_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"5f34ba27f46d5567a9b62da4a3914c45","title":
		 * "��������˽���ʷ����ȫ\u201c��������\u201d �������������ѻ���"
		 * ,"date":"2017-10-07 11:45","category"
		 * :"ͷ��","author_name":"����ĻῪ��","url"
		 * :"http://mini.eastday.com/mobile/171007114522237.html"
		 * ,"thumbnail_pic_s":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114522_b8403effc5cb6354428254069d03f146_2_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114522_b8403effc5cb6354428254069d03f146_5_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114522_b8403effc5cb6354428254069d03f146_4_mwpm_03200403.jpg"
		 * },{"uniquekey":"4550f339de305207aa133e31a93fdb91","title":
		 * "��Ϊ�ֻ��������ر��ſἼ�� ����֪���ļ�ֱ����Ϊ�ˣ�"
		 * ,"date":"2017-10-07 11:40","category":"ͷ��"
		 * ,"author_name":"��ȶ���","url":
		 * "http://mini.eastday.com/mobile/171007114037513.html"
		 * ,"thumbnail_pic_s":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114037_e5b229bb8ea690a6694ec376c9740b88_4_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114037_e5b229bb8ea690a6694ec376c9740b88_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007114037_e5b229bb8ea690a6694ec376c9740b88_2_mwpm_03200403.jpg"
		 * },{"uniquekey":"69f7b250b02ad8b2036e7280a08735c8","title":
		 * "�����������⹥�� �����ҷ�������ͦ�������ɾ����"
		 * ,"date":"2017-10-07 11:38","category":"ͷ��","author_name"
		 * :"�����","url":"http://mini.eastday.com/mobile/171007113835851.html"
		 * ,"thumbnail_pic_s":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007113835_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007113835_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg"
		 * },{"uniquekey":"aa3f25483fc2d91910f235fc4c5b188e","title":
		 * "�й�װ����ǿ���ڣ�ֱ���˵�����ظ�ԭ������ĳ���ò������"
		 * ,"date":"2017-10-07 11:35","category":"ͷ��"
		 * ,"author_name":"�˻�����","url":
		 * "http://mini.eastday.com/mobile/171007113519526.html"
		 * ,"thumbnail_pic_s":
		 * "http://01.imgmini.eastday.com/mobile/20171007/20171007_65f13f5b1d7ca005b190b25e69838031_cover_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://01.imgmini.eastday.com/mobile/20171007/20171007_22fe611fe8f9605d89d42cd63060ee1d_cover_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://01.imgmini.eastday.com/mobile/20171007/20171007_a6f3e5be81fa2c710ff36226095e91e6_cover_mwpm_03200403.jpg"
		 * },{"uniquekey":"abda5f029cfb76f348dde8d224daecb5","title":
		 * "���쳤�ٷ��̸߷�������Ļ"
		 * ,"date":"2017-10-07 11:28","category":"ͷ��","author_name"
		 * :"�й�������","url":"http://mini.eastday.com/mobile/171007112859305.html",
		 * "thumbnail_pic_s":
		 * "http://07.imgmini.eastday.com/mobile/20171007/20171007112859_603c0fee7e12a4b4ca00464d427fda63_2_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://07.imgmini.eastday.com/mobile/20171007/20171007112859_603c0fee7e12a4b4ca00464d427fda63_5_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://07.imgmini.eastday.com/mobile/20171007/20171007112859_603c0fee7e12a4b4ca00464d427fda63_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"d68f822ab86ceac58979f29a044f591b","title":
		 * "��10�ֶ����Ƿ��޹�ͬ�Ʋ�����֪����"
		 * ,"date":"2017-10-07 11:24","category":"ͷ��","author_name"
		 * :"��ʦ˵","url":"http://mini.eastday.com/mobile/171007112412479.html"
		 * ,"thumbnail_pic_s":
		 * "http://03.imgmini.eastday.com/mobile/20171007/20171007112412_cbc19ec7d8fb35ce3a633dc44b1cf047_3_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://03.imgmini.eastday.com/mobile/20171007/20171007112412_cbc19ec7d8fb35ce3a633dc44b1cf047_2_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://03.imgmini.eastday.com/mobile/20171007/20171007112412_cbc19ec7d8fb35ce3a633dc44b1cf047_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"2a51d2eb95272a786d1908169a6a13eb","title":
		 * "��3����Ф���������ޣ����ĺ����Ǻÿ��ֺ�������Ϣ����С"
		 * ,"date":"2017-10-07 11:19","category":"ͷ��"
		 * ,"author_name":"��Ф��������","url"
		 * :"http://mini.eastday.com/mobile/171007111922621.html"
		 * ,"thumbnail_pic_s":
		 * "http://06.imgmini.eastday.com/mobile/20171007/20171007111922_cacd45f4a6fb85305b65aa3c99bbac01_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://06.imgmini.eastday.com/mobile/20171007/20171007111922_cacd45f4a6fb85305b65aa3c99bbac01_2_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://06.imgmini.eastday.com/mobile/20171007/20171007111922_cacd45f4a6fb85305b65aa3c99bbac01_3_mwpm_03200403.jpg"
		 * },{"uniquekey":"5db5234bd60598ab027b36f4cf1d602e","title":
		 * "���Ϲ�ȷ�������Ŀ�� �����й���ǰʮ��ʵ��"
		 * ,"date":"2017-10-07 11:15","category":"ͷ��","author_name"
		 * :"������","url":"http://mini.eastday.com/mobile/171007111516316.html"
		 * ,"thumbnail_pic_s":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007111516_ced37bc2bba49d00a38ac0ea1beedd6b_10_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007111516_ced37bc2bba49d00a38ac0ea1beedd6b_2_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007111516_ced37bc2bba49d00a38ac0ea1beedd6b_6_mwpm_03200403.jpg"
		 * },{"uniquekey":"eb543e652e001388eb59a76e18bae99a","title":
		 * "�����ڣ�����˹8���ط��쵼����ְ"
		 * ,"date":"2017-10-07 11:13","category":"ͷ��","author_name"
		 * :"�»���","url":"http://mini.eastday.com/mobile/171007111355358.html"
		 * ,"thumbnail_pic_s":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007111355_85fd06a4ba9a0c128c3cd6473cbd0f28_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"9c47c82c3e442e6780c7a7a64448bc79","title":
		 * "��Щʳ�ﺬ�д��������棬�ܶ���ȴ�ԵĽ����ζ���Ͻ����ˣ�"
		 * ,"date":"2017-10-07 11:04","category":"ͷ��"
		 * ,"author_name":"�����ô","url":
		 * "http://mini.eastday.com/mobile/171007110438668.html"
		 * ,"thumbnail_pic_s":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007110438_9a18ac2b08186278c87866b8ba2ca428_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007110438_9a18ac2b08186278c87866b8ba2ca428_3_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://08.imgmini.eastday.com/mobile/20171007/20171007110438_9a18ac2b08186278c87866b8ba2ca428_4_mwpm_03200403.jpg"
		 * },{"uniquekey":"48480edb7a544708a05a2e9cd6ee3e07","title":
		 * "������У�ſ��������20��Ǯһ�̣����ϴ��������¶�����룡"
		 * ,"date":"2017-10-07 11:01","category":"ͷ��"
		 * ,"author_name":"�Ի��ӳ�","url":
		 * "http://mini.eastday.com/mobile/171007110152255.html"
		 * ,"thumbnail_pic_s":
		 * "http://09.imgmini.eastday.com/mobile/20171007/20171007110152_7b5301f017cf3f5d7a8c9be249a7e419_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://09.imgmini.eastday.com/mobile/20171007/20171007110152_7b5301f017cf3f5d7a8c9be249a7e419_4_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://09.imgmini.eastday.com/mobile/20171007/20171007110152_7b5301f017cf3f5d7a8c9be249a7e419_3_mwpm_03200403.jpg"
		 * },{"uniquekey":"a65405a0984c1a38cda4dff12c86ef1a","title":
		 * "\u200b���Ͻ����й���������һ���ִ���ս����ר�ҵĻ������Ŀ����"
		 * ,"date":"2017-10-07 10:57","category"
		 * :"ͷ��","author_name":"ȫ�۹�����","url"
		 * :"http://mini.eastday.com/mobile/171007105755058.html"
		 * ,"thumbnail_pic_s":
		 * "http://07.imgmini.eastday.com/mobile/20171007/20171007_9a74ec87118b7d72a60b7506b00401fc_cover_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://07.imgmini.eastday.com/mobile/20171007/20171007_b1c1b959864aa36779b04bb23cc73db7_cover_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://07.imgmini.eastday.com/mobile/20171007/20171007_bb26080dc52ababa4d9ff2bcb785156d_cover_mwpm_03200403.jpg"
		 * },{"uniquekey":"35efbc5f40f787616647e8d58d51a918","title":
		 * "����������ɽ���ձ�Ů������ʮ��֪��������˭���������ҹ������"
		 * ,"date":"2017-10-07 10:55","category":
		 * "ͷ��","author_name":"��ʷ��ɭ","url":
		 * "http://mini.eastday.com/mobile/171007105551620.html"
		 * ,"thumbnail_pic_s":
		 * "http://01.imgmini.eastday.com/mobile/20171007/20171007105551_db11de4956a7a9863752dd7e14b239d1_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://01.imgmini.eastday.com/mobile/20171007/20171007105551_db11de4956a7a9863752dd7e14b239d1_5_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://01.imgmini.eastday.com/mobile/20171007/20171007105551_db11de4956a7a9863752dd7e14b239d1_3_mwpm_03200403.jpg"
		 * },{"uniquekey":"93524993dfa35151fae749efc6fe18c6","title":
		 * "���⿪�ż����»��� ������Ӯ�����»���"
		 * ,"date":"2017-10-07 10:53","category":"ͷ��","author_name"
		 * :"������","url":"http://mini.eastday.com/mobile/171007105349780.html"
		 * ,"thumbnail_pic_s":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007105349_3379bcf00504bacc829a56ca1ac3eaab_2_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007105349_3379bcf00504bacc829a56ca1ac3eaab_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"3e1dfbc901e7fcbcc668e5ad51a3f66b","title":
		 * "ר�ҳ���ô��ȥ����ᱻ�Ͽ壡�й�����̫���ˣ�������˹���к�"
		 * ,"date":"2017-10-07 10:48","category":"ͷ��"
		 * ,"author_name":"����ͼ���","url"
		 * :"http://mini.eastday.com/mobile/171007104829831.html"
		 * ,"thumbnail_pic_s":
		 * "http://06.imgmini.eastday.com/mobile/20171007/20171007_d8a3e47ea8b2020fbfb28671f8429165_cover_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://06.imgmini.eastday.com/mobile/20171007/20171007_5d59af4f99450f3cb794d0b01c020bc9_cover_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://06.imgmini.eastday.com/mobile/20171007/20171007_08df451926949c6bf69e4438631b5d5d_cover_mwpm_03200403.jpg"
		 * },{"uniquekey":"a6717cecadbdf202880ce8c1e09e3fc5","title":
		 * "Ů�����񻹹��������£����Ѽ��������ܹܣ�"
		 * ,"date":"2017-10-07 10:45","category":"ͷ��","author_name"
		 * :"���ִ���","url":"http://mini.eastday.com/mobile/171007104535744.html",
		 * "thumbnail_pic_s":
		 * "http://04.imgmini.eastday.com/mobile/20171007/20171007104535_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://04.imgmini.eastday.com/mobile/20171007/20171007104535_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://04.imgmini.eastday.com/mobile/20171007/20171007104535_d41d8cd98f00b204e9800998ecf8427e_5_mwpm_03200403.jpg"
		 * },{"uniquekey":"14f05c508f0435d379310cdff2b330b5","title":
		 * "ʮһ���Ӿ���ץ��1812��ռ����\u201c����ͨ��\u201dΥ��"
		 * ,"date":"2017-10-07 10:38","category"
		 * :"ͷ��","author_name":"�Ϲ�����","url":
		 * "http://mini.eastday.com/mobile/171007103841926.html"
		 * ,"thumbnail_pic_s":
		 * "http://06.imgmini.eastday.com/mobile/20171007/20171007103841_702f8609b4003fe647dd5b97cbe8f824_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"9159d12c7cff9b9035db084a2313f177","title":
		 * "��֪ʶح������ʲô�磿ʱ��350����ĵ���250����2��"
		 * ,"date":"2017-10-07 10:38","category":"ͷ��"
		 * ,"author_name":"΢�Ź��ں�\u201c����ͷ��\u201d"
		 * ,"url":"http://mini.eastday.com/mobile/171007103812510.html"
		 * ,"thumbnail_pic_s":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007103812_c2059749ba77af0f1e9f7d73f9f55e0c_4_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007103812_c2059749ba77af0f1e9f7d73f9f55e0c_1_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007103812_c2059749ba77af0f1e9f7d73f9f55e0c_5_mwpm_03200403.jpg"
		 * },{"uniquekey":"4cc1664d4b6a3dc76df527f9a22b7714","title":
		 * "Ӣ��������ɫ�����ֻ�ʯ�����泾��"
		 * ,"date":"2017-10-07 10:37","category":"ͷ��","author_name"
		 * :"�»���","url":"http://mini.eastday.com/mobile/171007103725196.html"
		 * ,"thumbnail_pic_s":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007103725_84b21ece3511f8e5b149c929879df841_3_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007103725_84b21ece3511f8e5b149c929879df841_2_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://00.imgmini.eastday.com/mobile/20171007/20171007103725_84b21ece3511f8e5b149c929879df841_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"eb8e16fc9a7eb26e26212ff0dd6fd0ff","title":
		 * "ӡ��ƶ�����벻����ǿ���������Ҫ�ϳ�����Ǹ��ģ�"
		 * ,"date":"2017-10-07 10:34","category":"ͷ��",
		 * "author_name":"����������","url"
		 * :"http://mini.eastday.com/mobile/171007103434319.html"
		 * ,"thumbnail_pic_s":
		 * "http://07.imgmini.eastday.com/mobile/20171007/20171007103434_5bfe5ccefcf0f742fd25d5ff09e84e09_5_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s02":
		 * "http://07.imgmini.eastday.com/mobile/20171007/20171007103434_5bfe5ccefcf0f742fd25d5ff09e84e09_2_mwpm_03200403.jpg"
		 * ,"thumbnail_pic_s03":
		 * "http://07.imgmini.eastday.com/mobile/20171007/20171007103434_5bfe5ccefcf0f742fd25d5ff09e84e09_4_mwpm_03200403.jpg"
		 * },{"uniquekey":"1e22470dc05bd4d9e3f7aef784e53811","title":
		 * "�й����г���һ�򣺹����������� ������ɱ����"
		 * ,"date":"2017-10-07 10:16","category":"ͷ��","author_name"
		 * :"�ƾ������ܿ�","url"
		 * :"http://mini.eastday.com/mobile/171007101656225.html",
		 * "thumbnail_pic_s":
		 * "http://02.imgmini.eastday.com/mobile/20171007/20171007101656_9bedcb8cffc33a5a637d33d4630ffec5_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"f361a05b5bc9eea863c315867f1eb01b","title":
		 * "�й���ѧ��ʿ���ӱ߾�����ʧ�� ��ʽ����������"
		 * ,"date":"2017-10-07 10:12","category":"ͷ��","author_name"
		 * :"�����ձ������-������"
		 * ,"url":"http://mini.eastday.com/mobile/171007101206331.html"
		 * ,"thumbnail_pic_s":
		 * "http://06.imgmini.eastday.com/mobile/20171007/20171007101206_44254a89ff826efb097e290a507ba4dc_1_mwpm_03200403.jpg"
		 * },{"uniquekey":"e3ca4572333f697668fc611b69ca1bea","title":
		 * "������ӭ�Լ��η��̸߷塡Ԥ��15:30-20:00���"
		 * ,"date":"2017-10-07 10:08","category":"ͷ��"
		 * ,"author_name":"������-�人��","url"
		 * :"http://mini.eastday.com/mobile/171007100843827.html"
		 * ,"thumbnail_pic_s":
		 * "http://01.imgmini.eastday.com/mobile/20171007/20171007100843_30f441bb06659d3b39f18689de84fc1f_1_mwpm_03200403.jpg"
		 * }]
		 */

		private String stat;
		private List<DataBean> data;

		public String getStat() {
			return stat;
		}

		public void setStat(String stat) {
			this.stat = stat;
		}

		public List<DataBean> getData() {
			return data;
		}

		public void setData(List<DataBean> data) {
			this.data = data;
		}

		public static class DataBean {
			/**
			 * uniquekey : 4493800c45fd126f8653244e9fa65bc2 title : ����׹��ȴл������ԱӪ��
			 * ԭ�ǾƼݺ�������ܴ��� date : 2017-10-07 13:06 category : ͷ�� author_name :
			 * �������� url : http://mini.eastday.com/mobile/171007130632169.html
			 * thumbnail_pic_s : http://05.imgmini.eastday.com/mobile/20171007/
			 * 20171007130632_29a34c5a273dd40a3e7d81774334a4af_2_mwpm_03200403.jp
			 * g thumbnail_pic_s02 :
			 * http://05.imgmini.eastday.com/mobile/20171007/
			 * 20171007130632_29a34c5a273dd40a3e7d81774334a4af_3_mwpm_03200403.jp
			 * g thumbnail_pic_s03 :
			 * http://05.imgmini.eastday.com/mobile/20171007/
			 * 20171007130632_29a34c5a273dd40a3e7d81774334a4af_1_mwpm_03200403.jp
			 * g
			 */

			private String uniquekey;
			private String title;
			private String date;
			private String category;
			private String author_name;
			private String url;
			private String thumbnail_pic_s;
			private String thumbnail_pic_s02;
			private String thumbnail_pic_s03;

			public String getUniquekey() {
				return uniquekey;
			}

			public void setUniquekey(String uniquekey) {
				this.uniquekey = uniquekey;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getDate() {
				return date;
			}

			public void setDate(String date) {
				this.date = date;
			}

			public String getCategory() {
				return category;
			}

			public void setCategory(String category) {
				this.category = category;
			}

			public String getAuthor_name() {
				return author_name;
			}

			public void setAuthor_name(String author_name) {
				this.author_name = author_name;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public String getThumbnail_pic_s() {
				return thumbnail_pic_s;
			}

			public void setThumbnail_pic_s(String thumbnail_pic_s) {
				this.thumbnail_pic_s = thumbnail_pic_s;
			}

			public String getThumbnail_pic_s02() {
				return thumbnail_pic_s02;
			}

			public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
				this.thumbnail_pic_s02 = thumbnail_pic_s02;
			}

			public String getThumbnail_pic_s03() {
				return thumbnail_pic_s03;
			}

			public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
				this.thumbnail_pic_s03 = thumbnail_pic_s03;
			}
		}
	}
}
