package com.lt.dailytest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lt.dailytest.dao.StockBuyTableMapper;
import com.lt.dailytest.dao.StockMainTableMapper;
import com.lt.dailytest.dao.StockSellTableMapper;
import com.lt.dailytest.dao.StockTableMapper;
import com.lt.dailytest.entity.StockBuyTable;
import com.lt.dailytest.entity.StockMainTable;
import com.lt.dailytest.entity.StockSellTable;
import com.lt.dailytest.entity.StockTable;
import com.lt.dailytest.service.StockTableService;
import com.lt.dailytest.utils.common.DateUtils;
import com.lt.dailytest.utils.http.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author tong.luo
 * @description StockTableServiceImpl
 * @date 2021/7/14 14:03
 */
@Service
public class StockTableServiceImpl implements StockTableService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockTableServiceImpl.class);

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() + 2,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    @Autowired
    StockTableMapper stockTableDao;
    @Autowired
    StockBuyTableMapper stockBuyTabseMapper;
    @Autowired
    StockMainTableMapper stockMainTableMapper;
    @Autowired
    StockSellTableMapper stockSellTableMapper;
    private List<String> nowInLowList = new ArrayList<>(1024);
    private static List<String> nowInUseList = new ArrayList<>(1024);

    /*static {
        nowInUseList = Arrays.asList("000932", "000851", "000036", "000913", "000108", "000933", "000991", "000814", "000857", "000103", "000109", "000126", "000129", "000121", "000074", "000093", "000148", "000125", "000075", "000147", "000037", "000053", "000091", "000136", "000010", "000130", "000067", "000849", "000051", "000111", "000150", "000097", "000123", "000146", "000858", "000142", "000069", "000118", "000099", "000115", "000982", "000011", "000132", "000120", "000993", "000802", "000852", "000135", "000128", "000905", "000823", "000819", "000856", "000107", "000102", "000974", "000914", "000170", "000009", "000096", "000117", "000138", "000934", "000935", "000006", "000989", "000992", "000077", "000106", "000057", "000854", "000137", "000105", "000028", "000901", "000119", "000906", "000863", "000018", "000300", "000045", "000133", "000100", "000903", "000038", "000112", "000039", "000145", "000046", "000076", "000098", "000987", "000044", "000064", "000141", "000059", "000007", "000058", "000047", "000071", "000035", "000029", "000065", "000095", "000073", "000159", "000002", "000060", "000092", "000001", "000867", "000155", "000016", "000063", "000162", "000004", "000030", "000072", "000149", "000026", "000153", "000110", "000005", "000033", "000017", "000031", "000049", "000008", "000869", "000015", "000052", "000034", "000847", "000040", "000043", "000094", "000050", "000827", "000066", "000068", "000078", "000131", "000161", "000062", "000070", "000113", "000158", "000122", "000853", "600519", "000079", "000152", "000041", "000891", "000928", "000160", "000855", "000114", "000986", "000042", "000688", "000048", "000055", "000020", "000025", "000865", "000090", "000054", "000032", "000056", "000151", "000860", "000019", "000104", "000021", "000027", "000134", "603444", "600436", "113016", "600809", "600763", "000139", "603290", "603501", "113582", "601888", "603392", "113526", "000003", "113580", "110044", "603486", "600702", "605499", "603185", "603489", "110074", "601799", "603026", "603195", "113025", "113509", "000022", "000101", "603345", "113527", "600132", "110055", "113047", "000116", "000012", "603127", "603605", "113034", "113534", "113548", "110066", "113611", "113545", "603236", "603986", "603737", "600563", "113537", "113550", "113504", "603259", "000061", "113621", "113616", "605111", "113615", "603659", "113049", "113508", "600779", "110048", "113623", "118000", "113040", "605168", "113585", "603833", "603267", "603799", "110061", "113575", "603893", "113612", "113012", "603882", "603129", "605117", "600309", "603288", "113568", "113618", "132014", "113602", "113607", "511010", "110075", "113541", "113614", "110053", "113528", "605358", "110033", "110063", "110077", "113606", "132017", "110047", "113050", "113572", "113577", "113579", "132018", "110070", "113525", "113603", "113014", "113039", "603613", "605369", "110051", "132020", "110045", "113011", "113048", "113502", "113536", "113625", "110043", "113009", "113609", "600486", "110079", "113013", "113605", "113608", "511260", "110057", "113045", "113597", "511310", "110060", "111000", "113030", "113610", "113622", "511280", "113516", "113561", "113567", "110062", "110071", "113542", "113566", "113594", "122667", "110034", "113574", "113593", "113598", "120303", "122374", "124616", "163692", "163926", "603160", "603806", "110067", "110073", "110076", "113043", "113524", "113563", "113565", "113588", "113599", "132021", "147180", "511020", "110038", "110041", "110056", "113021", "113546", "113549", "113570", "122737", "143531", "110058", "113026", "113505", "113604", "132008", "147727", "155083", "511270", "110068", "110080", "113027", "113046", "113591", "132007", "140981", "143588", "147510", "147763", "110059", "113037", "113044", "113519", "113573", "113600", "113619", "122260", "132009", "147857", "152001", "155664", "155681", "157069", "113024", "113042", "113530", "113535", "113578", "113620", "113624", "120201", "122168", "122194", "124935", "132004", "132015", "143235", "143237", "143305", "143631", "147328", "147551", "147592", "147662", "147716", "147771", "152035", "152043", "152663", "155806", "157530", "157673", "511360", "600600", "110052", "113532", "122734", "122742", "132016", "143027", "143071", "143349", "147955", "152789", "155087", "155229", "155251", "155624", "155943", "157537", "157711", "175577", "511060", "511880", "113033", "113036", "113601", "120608", "124915", "127175", "127451", "127574", "127878", "130743", "132011", "132022", "136101", "136139", "136232", "136259", "136601", "136629", "136648", "136710", "136726", "136739", "136778", "136819", "136845", "136886", "136919", "136926", "140048", "140940", "143045", "143075", "143125", "143161", "143210", "143270", "143352", "143764", "143820", "147497", "152075", "152127", "152156", "152276", "152316", "152335", "152403", "152497", "152580", "152600", "152636", "152673", "152805", "152850", "152869", "155003", "155074", "155100", "155134", "155211", "155281", "155384", "155436", "155463", "155499", "155539", "155602", "155753", "155962", "157370", "160075", "163098", "163351", "163418", "163722", "163874", "163989", "175037", "175160", "175214", "175235", "175289", "175302", "175454", "175603", "175685", "188064", "511660", "511690", "511700", "511800", "511850", "511950", "511980", "511990", "110064", "122443", "122496", "130859", "136135", "136556", "136569", "136592", "136596", "136624", "136670", "136674", "136694", "136770", "136822", "140164", "143042", "143068", "143107", "143265", "143477", "143749", "143750", "143835", "143865", "152458", "152490", "152633", "155055", "155063", "155117", "155125", "155142", "155525", "155712", "155766", "157153", "160120", "163049", "163077", "163084", "163091", "163094", "163142", "163169", "163571", "163705", "175023", "175034", "175165", "175252", "175366", "175441", "175613", "511600", "511650", "511620", "511670", "511770", "511810", "511820", "511830", "511860", "511900", "511910", "511920", "511930", "511960", "511970", "603713", "113017", "113569", "113584", "124977", "127442", "127875", "130630", "136279", "136792", "136823", "143013", "143197", "152237", "152386", "152396", "152470", "152684", "155138", "155620", "155989", "160980", "163013", "163038", "163316", "163507", "163547", "163644", "175049", "175259", "124350", "140092", "140351", "140375", "140903", "143857", "152313", "152366", "160333", "160337", "163191", "163211", "163216", "163472", "124815", "136893", "143225", "152389", "152476", "155148", "155458", "155638", "155985", "163259", "163506", "511220", "600745", "136360", "143159", "155194", "155195", "155339", "163023", "163034", "163101", "163635", "113589", "124044", "136491", "143467", "152430", "155294", "155405", "155634", "155666", "601100", "155280", "155451", "155471", "155491", "155635", "163188", "163398", "110072", "143103", "152090", "163224", "113576", "113596", "155305", "603666", "601012", "127856", "143345", "143344", "175375", "603087", "603208", "122249", "163373", "603260", "600570", "113595", "605266", "127776", "127797", "127820", "603517", "603899", "605089", "605376", "127585", "127524", "127579", "155407", "127534", "127655", "127843", "152032", "122393", "152003", "152016", "155406", "127638", "127660", "127599", "603068", "603039", "603678", "603589", "603816", "127677", "127634", "603658", "605288", "127621", "127587", "600196", "605009", "605123", "603416", "603786", "600885", "601127", "127681", "127486", "127492", "600760", "600976", "124970", "127026", "127474", "127483", "127507", "127490", "127491", "603396", "603755", "605098", "601318", "603297", "600276", "600536", "600460", "600845", "603877", "605080", "127458", "127489", "127443", "600211", "601995", "603369", "605389", "603338", "127532", "603171", "605337", "600298", "600882", "601021", "603005", "603529", "605305", "600315", "600754", "600612", "603076", "603378", "603520", "603583", "603650", "603939", "603155", "603661", "600036", "600660", "600038", "603324", "603738", "603938", "603690", "603883", "124575", "600893", "124500", "124735", "124805", "124407", "124713", "601336", "603456", "603983", "605296", "600696", "600009", "600456", "603233", "603306", "603722", "603919", "603991", "605077", "601633", "124391", "600161", "600771", "603301", "603995", "603098", "603187", "603638", "603733", "603906", "605378", "605398", "601966", "603180", "603186", "603198", "603538", "600872", "127242", "127283", "127377", "127386", "127415", "127319", "127380", "127395", "127416", "601865", "603868", "605100", "605186", "605339", "600259", "600438", "600753", "600316", "600584", "600585", "601155", "603719", "603896", "605099", "605128", "603025", "603859", "603915", "605081", "600171", "600426", "600887", "600990", "127351", "127427", "603596", "603826", "603976", "605178", "605287", "603579", "600085", "600529", "603197", "603535", "603590", "605180", "127186", "603223", "603305", "603712", "601689", "603110", "603156", "603317", "603355", "603511", "603881", "603931", "603989", "605068", "605177", "605259", "605338", "600332", "600588", "600884", "600111", "600389", "600703", "601628", "601877", "603096", "603217", "603229", "603429", "603599", "603867", "603960", "605090", "124290", "124228", "124234", "600511", "603008", "603179", "603657", "603707", "603822", "605258", "603019", "603203", "603801", "603866", "603927", "605016", "605300", "122631", "124126", "124232", "600197", "600862", "600079", "600346", "601066", "603228", "603348", "603353", "603505", "605136", "605336", "601601", "603016", "603027", "603212", "603218", "603279", "603565", "603876", "605028", "605488", "600363", "600690", "600729", "600993", "600118", "600238", "600329", "600406", "600549", "600699", "600764", "600850", "600859", "601233", "603031", "603283", "603337", "603568", "603773", "603897", "605289", "603043", "603258", "603380", "603383", "603515", "603533", "603566", "603587", "603681", "605011", "605086", "605122", "605199", "600031", "600378", "600459", "600559", "600587", "600876", "603078", "603518", "603595", "603688", "603908", "605003", "127268", "600030", "600055", "600141", "600183", "600362", "601869", "603059", "603225", "603319", "603330", "603987", "603990", "605399", "601677", "603095", "603109", "603192", "603379", "603606", "603629", "603700", "605058", "605189", "605208", "600258", "600295", "600741", "600392", "600521", "600645", "600739", "600765", "601636", "601919", "603010", "603313", "603507", "603516", "603787", "603858", "603970", "605008", "605155", "605198", "603214", "603277", "603308", "603387", "603630", "603757", "603836", "603860", "603916", "603992", "605133", "605222", "605277", "605388", "122616", "124781", "124882", "124913", "127157", "127236", "600104", "600143", "600323", "600338", "600596", "600685", "600900", "601156", "601166", "600129", "600265", "600391", "600694", "600746", "600848", "601607", "601882", "603040", "603069", "603199", "603351", "603368", "603508", "603697", "603759", "603948", "603950", "603978", "603979", "605286", "127029", "127095", "127096", "127111", "127123", "127150", "127202", "127223", "600201", "600305", "600399", "600498", "600641", "600861", "600895", "600999", "601566", "601615", "603032", "603060", "603189", "603386", "603639", "603655", "603662", "603825", "603855", "605007", "605108", "605151", "605298", "601696", "601965", "603033", "603038", "603100", "603113", "603131", "603232", "603298", "603332", "603393", "603466", "603530", "603610", "603612", "603709", "603727", "603730", "603739", "603776", "603888", "603895", "603912", "603956", "603985", "605179", "605183", "605299", "605303", "605377", "605500", "600328", "600455", "600532", "600547", "600803", "600827", "601028", "601088", "600007", "600176", "600188", "600199", "600216", "600230", "600482", "600499", "600566", "600610", "600773", "600774", "600801", "600897", "600980", "601211", "601567", "601788", "603020", "603063", "603139", "603181", "603200", "603239", "603303", "603358", "603588", "603698", "603708", "603711", "603728", "603808", "603871", "603886", "603982", "603988", "605319", "601688", "603037", "603079", "603136", "603159", "603268", "603567", "603656", "603663", "603679", "603686", "603721", "603768", "603777", "603809", "603811", "603823", "603901", "603909", "603922", "603926", "605005", "605060", "605088", "605162", "124057", "600053", "600081", "600096", "600110", "600150", "600182", "600345", "600372", "600452", "600621", "600648", "600663", "600835", "600906", "600916", "600988", "601038", "600060", "600089", "600137", "600206", "600234", "600418", "600562", "600598", "600639", "600732", "600756", "600817", "600989", "600998", "601163", "601208", "601231", "601238", "601456", "601698", "603000", "603093", "603103", "603161", "603222", "603238", "603286", "603309", "603327", "603408", "603496", "603519", "603585", "603607", "603665", "603693", "603717", "603718", "603726", "603729", "603810", "603885", "603937", "605006", "605020", "605118", "605169", "605255", "605318", "122679", "600071", "600131", "600223", "600262", "600267", "600271", "600416", "600446", "600523", "600535", "600597", "600750", "600877", "600926", "600933", "601168", "601236", "601528", "601808", "601969", "603013", "603029", "603035", "603050", "603083", "603090", "603115", "603138", "603177", "603322", "603360", "603365", "603439", "603506", "603578", "603586", "603611", "603706", "603813", "603898", "603933", "603949", "603955", "603968", "605018", "605066", "605116", "605218", "605333", "605368", "601360", "601699", "601878", "603022", "603042", "603128", "603158", "603165", "603266", "603300", "603315", "603329", "603367", "603559", "603685", "603716", "603788", "603789", "603848", "603920", "605268", "600059", "600066", "600148", "600352", "600380", "600409", "600419", "600444", "600487", "600580", "600674", "600697", "600776", "600785", "600793", "600810", "600855", "600956", "600966", "600977", "601069", "601200", "601311", "600032", "600048", "600056", "600062", "600072", "600095", "600109", "600158", "600160", "600189", "600195", "600283", "600299", "600306", "600371", "600375", "600476", "600483", "600557", "600640", "600647", "600661", "600668", "600679", "600733", "600783", "600834", "600837", "600867", "600963", "600985", "601137", "601138", "601579", "601828", "601838", "603041", "603045", "603080", "603121", "603220", "603320", "603477", "603536", "603551", "603556", "603600", "603601", "603628", "603633", "603677", "603680", "603703", "603890", "603936", "603959", "603967", "603966", "605196", "605366", "601595", "601606", "601686", "601717", "601952", "601963", "601990", "603017", "603056", "603081", "603108", "603112", "603133", "603221", "603269", "603311", "603326", "603331", "603356", "603359", "603385", "603458", "603580", "603598", "603602", "603609", "603617", "603667", "603790", "603798", "603800", "603829", "603856", "603903", "603917", "603928", "603929", "605050", "511030", "511180", "511380", "600004", "600138", "600155", "600184", "600251", "600285", "600288", "600367", "600373", "600449", "600475", "600480", "600515", "600522", "600573", "600619", "600636", "600655", "600682", "600720", "600749", "600761", "600812", "600818", "600857", "600875", "600918", "600958", "600970", "600984", "601009", "601108", "601198", "601225", "600000", "600054", "600070", "600088", "600151", "600167", "600193", "600203", "600233", "600330", "600343", "600377", "600379", "600381", "600383", "600420", "600422", "600508", "600556", "600586", "600592", "600618", "600623", "600650", "600671", "600707", "600718", "600727", "600737", "600742", "600744", "600822", "600841", "600847", "600851", "600886", "600903", "600967", "600983", "601058", "601098", "601187", "601279", "601377", "601512", "601827", "601881", "601899", "601901", "601908", "601968", "603001", "603028", "603053", "603086", "603089", "603105", "603126", "603168", "603178", "603278", "603289", "603339", "603363", "603377", "603390", "603398", "603500", "603615", "603619", "603636", "603669", "603683", "603689", "603696", "603699", "603839", "603869", "603887", "605166", "605228", "601330", "601369", "601519", "601555", "601568", "601577", "601609", "601665", "601678", "601811", "601858", "603006", "603055", "603066", "603067", "603099", "603118", "603166", "603167", "603256", "603357", "603528", "603558", "603577", "603626", "603648", "603701", "603758", "603803", "603815", "603818", "603819", "603861", "603863", "603900", "603958", "603997", "605001", "605055", "605158", "605188", "600037", "600058", "600061", "600064", "600097", "600114", "600116", "600123", "600127", "600152", "600200", "600218", "600228", "600246", "600260", "600273", "600292", "600327", "600360", "600388", "600479", "600489", "600501", "600513", "600516", "600543", "600548", "600560", "600577", "600605", "600616", "600637", "600662", "600667", "600711", "600714", "600768", "600826", "600833", "600846", "600865", "600917", "601007", "601015", "601020", "601116", "601117", "601126", "601177", "601222", "508001", "510500", "510580", "510590", "601886", "601898", "601900", "601928", "601956", "601997", "603003", "603018", "603036", "603196", "603299", "603321", "603328", "603421", "603488", "603499", "603527", "603637", "603660", "603668", "603676", "603687", "603725", "603779", "603797", "603879", "603918", "603963", "603977", "600006", "600019", "600057", "600068", "600073", "600075", "600080", "600119", "600153", "600165", "600172", "600178", "600185", "600198", "600207", "600213", "600257", "600272", "600278", "600284", "600301", "600318", "600326", "600348", "600354", "600356", "600359", "600366", "600395", "600398", "600410", "600435", "600458", "600461", "600481", "600500", "600505", "600506", "600507", "600517", "600520", "600528", "600538", "600539", "600546", "600552", "600551", "600571", "600599", "600615", "600620", "600624", "600628", "600633", "600723", "600728", "600731", "600755", "600789", "600802", "600836", "600883", "600962", "600992", "600997", "601001", "601111", "601186", "601229", "600012", "600021", "600026", "600039", "600051", "600083", "600098", "600099", "600113", "600128", "600135", "600136", "600168", "600173", "600177", "600215", "600222", "600237", "600241", "600268", "600325", "600350", "600385", "600405", "600509", "600510", "600525", "600579", "600581", "600590", "600608", "600643", "600686", "600689", "600704", "600706", "600710", "600722", "600738", "600767", "600769", "600770", "600775", "600782", "600797", "600814", "600838", "600843", "600874", "600879", "600888", "600889", "600894", "600905", "600919", "600929", "600961", "600969", "600971", "600975", "600995", "601003", "601006", "601128", "601139", "601298", "510530", "515360", "515930", "601339", "601366", "601515", "601611", "601666", "601702", "601800", "601939", "601958", "601999", "603002", "603009", "603015", "603058", "603085", "603116", "603123", "603226", "603318", "603336", "603388", "603569", "603608", "603618", "603682", "603767", "603838", "603878", "603880", "603889", "603980", "603993", "601319", "601390", "601500", "601598", "601600", "601766", "601778", "601798", "601816", "601890", "601949", "601998", "603012", "603088", "603106", "603183", "603188", "603227", "603316", "603333", "603399", "603557", "603603", "603616", "603817", "603999", "603998", "600015", "600018", "600025", "600029", "600063", "600100", "600101", "600107", "600121", "600126", "600133", "600149", "600163", "600180", "600210", "600217", "600229", "600232", "600235", "600236", "600287", "600308", "600312", "600319", "600335", "600336", "600340", "600351", "600353", "600376", "600387", "600390", "600428", "600429", "600433", "600463", "600470", "600478", "600531", "600540", "600594", "600602", "600604", "600606", "600613", "600629", "600630", "600638", "600642", "600644", "600678", "600692", "600735", "600757", "600787", "600796", "600798", "600820", "600819", "600821", "600829", "600831", "600858", "600860", "600864", "600869", "600873", "600898", "600901", "600908", "600909", "600965", "600987", "600996", "601019", "601101", "601158", "601216", "510130", "510220", "510300", "510330", "510350", "510390", "512600", "515350", "515380", "515660", "515830", "510110", "510120", "510180", "510190", "510710", "513100", "601328", "601368", "601375", "601398", "601658", "601668", "601700", "601777", "601801", "601857", "601872", "601918", "601933", "601985", "601989", "603011", "603021", "603030", "603101", "603111", "603169", "603323", "603335", "603366", "603389", "603778", "603828", "603969", "600016", "600028", "600050", "600076", "600093", "600115", "600120", "600125", "600179", "600192", "600209", "600212", "600219", "600243", "600248", "600249", "600250", "600266", "600281", "600293", "600302", "600310", "600313", "600333", "600337", "600365", "600369", "600382", "600415", "600469", "600488", "600490", "600491", "600493", "600497", "600537", "600550", "600561", "600572", "600576", "600582", "600583", "600603", "600609", "600626", "600649", "600673", "600676", "600681", "600693", "600712", "600713", "600721", "600730", "600736", "600740", "600748", "600766", "600778", "600784", "600800", "600804", "600808", "600825", "600830", "600844", "600870", "600896", "600928", "600973", "600986", "601002", "601011", "601118", "601162", "601169", "600008", "600011", "600020", "600023", "600027", "600052", "600067", "600078", "600084", "600094", "600105", "600117", "600130", "600156", "600166", "600186", "600191", "600202", "600227", "600252", "600256", "600261", "600269", "600275", "600277", "600279", "600282", "600289", "600291", "600300", "600303", "600320", "600358", "600361", "600368", "600370", "600386", "600403", "600408", "600423", "600425", "600439", "600448", "600468", "600477", "600495", "600496", "600502", "600503", "600512", "600530", "600533", "600558", "600567", "600569", "600595", "600611", "600617", "600635", "600651", "600657", "600658", "600675", "600683", "600684", "600688", "600691", "600695", "600698", "600705", "600716", "600715", "600717", "600719", "600724", "600758", "600780", "600781", "600790", "600791", "600792", "600794", "600805", "600811", "600823", "600824", "600828", "600854", "600866", "600880", "600892", "600939", "600960", "600982", "601008", "601010", "601018", "601016", "601068", "601077", "601086", "601099", "601106", "601107", "601113", "601179", "601188", "601199", "601218", "601226", "601228", "601288", "501057", "508027", "508006", "508000", "508056", "510020", "510050", "510170", "510600", "510660", "510680", "510850", "512500", "515530", "515700", "518600", "518660", "518680", "518800", "518850", "518860", "518880", "518890", "601388", "601399", "601599", "601608", "601616", "601618", "601619", "601669", "601727", "601789", "601818", "601860", "601866", "601916", "601988", "601996", "603007", "603023", "603117", "603766", "603843", "603996", "601326", "601333", "601518", "601588", "601718", "601975", "601991", "601992", "603077", "603157", "603555", "204003", "204004", "204001", "204002", "204007", "204014", "204028", "204091", "204182", "501001", "501009", "501010", "501022", "501038", "501046", "501051", "501058", "501064", "501066", "501077", "501079", "501078", "501082", "501201", "510060", "510090", "510290", "510310", "510440", "510510", "510650", "510880", "512070", "512120", "512610", "512640", "512770", "513500", "513600", "513660", "515030", "515200", "600010", "600017", "600033", "600035", "600077", "600082", "600091", "600103", "600106", "600108", "600139", "600146", "600159", "600170", "600169", "600187", "600190", "600208", "600220", "600221", "600225", "600226", "600231", "600242", "600255", "600280", "600290", "600297", "600307", "600311", "600321", "600331", "600339", "600355", "600396", "600397", "600400", "600462", "600466", "600467", "600518", "600527", "600545", "600565", "600575", "600578", "600589", "600601", "600622", "600652", "600664", "600665", "600708", "600725", "600726", "600734", "600751", "600759", "600795", "600807", "600816", "600815", "600839", "600853", "600863", "600868", "600871", "600881", "600890", "600936", "600959", "600968", "600979", "600981", "601000", "601005", "601212", "600666", "512290");
    }*/


    /**
     * 同步所有数据
     */
    @Override
    public void synAllData() {

        Timer timer = new Timer();
        //1个小时更新一次
        Date date = new Date();
        String formatDate = DateUtils.formatDate(date, DateUtils.yyyy_MM_dd);
        Date beginDate = DateUtils.parseDate(formatDate + " 08:00:00", DateUtils.yyyy_MM_dd_hh_mm);
        Date endDate = DateUtils.parseDate(formatDate + " 15:30:00", DateUtils.yyyy_MM_dd_hh_mm);
        boolean exclude = date.compareTo(beginDate) > 0 && date.compareTo(endDate) < 0;
        List<StockTable> stockTables = this.stockTableDao.queryAll(new StockTable());
        Date startOfDay = DateUtils.getStartOfDay(new Date());
        nowInUseList = stockTables.stream()
                .map(StockTable::getStockNum)
                .collect(Collectors.toList());

        List<String> stringList = stockTables.stream()
                .filter(a -> a.getStatus() != null && a.getStatus() == 1 && a.getDealDate().compareTo(startOfDay) > 0)
                .map(StockTable::getStockNum)
                .collect(Collectors.toList());
        if (! CollectionUtils.isEmpty(stringList)) {
            nowInUseList = stringList;
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                if (exclude) {
                    if (CollectionUtils.isEmpty(nowInUseList)) {
                        loopGetInfo();
                    } else {
                        loopGetInfo(nowInUseList);
                    }
                }
                LOGGER.info("nowInUseList .size :{}", nowInUseList.size());
                LOGGER.info("doAssable logic cost time:[{}] :current:[{}]", (System.currentTimeMillis() - startTime),
                        DateUtils.formatDate(new Date(), DateUtils.yyyy_MM_dd_hh_mm_ss));
            }
        }, 0, 60 * 60 * 1000);

        while (true) {
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                LOGGER.error("thread sleep error", e);
            }
        }
    }

    @Override
    public void initAllData() {
        long start = System.currentTimeMillis();
        LOGGER.info("initAllData  --> begin" + start);
        loopGetInfo();
        LOGGER.info("initAllData  --> end" + (System.currentTimeMillis() - start));
    }

    /**
     * 拆分数据
     */
    @Override
    public void splateCurrDayAllData() {
        StockTable stockTable = new StockTable();
        String formatDate = DateUtils.formatDate(new Date(), DateUtils.yyyy_MM_dd);

        stockTable.setDealDateBegin(DateUtils.parseDate(formatDate + " 00:00:00", DateUtils.yyyy_MM_dd_hh_mm));
        stockTable.setDealDateEnd(DateUtils.parseDate(formatDate + " 59:59:59", DateUtils.yyyy_MM_dd_hh_mm));
        List<StockTable> stockTables = this.stockTableDao.queryAll(stockTable);
        if (CollectionUtils.isEmpty(stockTables)) {
            return;
        }
        int size = stockTables.size();
        int skipNum = 3000;
        Integer divide = new BigDecimal(String.valueOf(size)).divide(new BigDecimal(String.valueOf(skipNum)), BigDecimal.ROUND_UP).intValue();
        for (int i = 0; i < divide; i++) {
            List<StockTable> stockTableList = stockTables.stream().skip(i * skipNum).limit(skipNum).collect(Collectors.toList());
            List<StockMainTable> stockMainTableList = new ArrayList<>();
            List<StockBuyTable> stockBuyTables = new ArrayList<>();
            List<StockSellTable> stockSellTables = new ArrayList<>();
            for (StockTable table : stockTableList) {
                StockMainTable stockMainTable = new StockMainTable();
                BeanUtils.copyProperties(table, stockMainTable);
                stockMainTable.setId(null);
                stockMainTable.setCreateTime(new Date());
                stockMainTable.setUpdateTime(new Date());
                String stockName = stockMainTable.getStockName();
                String[] split = stockName.split("\"");
                if (split.length > 1) {
                    stockMainTable.setStockName(split[1]);
                }
                stockMainTableList.add(stockMainTable);
                StockBuyTable stockBuyTable = new StockBuyTable();
                BeanUtils.copyProperties(table, stockBuyTable);
                stockBuyTable.setId(null);
                stockBuyTable.setCreateTime(new Date());
                stockBuyTable.setUpdateTime(new Date());
                stockBuyTables.add(stockBuyTable);
                StockSellTable stockSellTable = new StockSellTable();
                BeanUtils.copyProperties(table, stockSellTable);
                stockSellTable.setId(null);
                stockSellTable.setCreateTime(new Date());
                stockSellTable.setUpdateTime(new Date());
                stockSellTables.add(stockSellTable);
            }
            if (CollectionUtils.isEmpty(stockMainTableList) || CollectionUtils.isEmpty(stockBuyTables) || CollectionUtils.isEmpty(stockSellTables)) {
                return;
            }
            this.stockMainTableMapper.deleteBystockNums(stockMainTableList.stream().map(StockMainTable::getStockNum).collect(Collectors.toList()), formatDate);
            this.stockBuyTabseMapper.deleteBystockNums(stockBuyTables.stream().map(StockBuyTable::getStockNum).collect(Collectors.toList()), formatDate);
            this.stockSellTableMapper.deleteBystockNums(stockSellTables.stream().map(StockSellTable::getStockNum).collect(Collectors.toList()), formatDate);
            this.stockMainTableMapper.insertBatch(stockMainTableList);
            this.stockBuyTabseMapper.insertOrUpdateBatch(stockBuyTables);
            this.stockSellTableMapper.insertBatch(stockSellTables);
        }


    }

    @Override
    public void synOneData(String stockNum) {
        if (StringUtils.isEmpty(stockNum)) {
            return;
        }
        this.doAssableInsertTable(stockNum);
    }

    @Override
    public void synAllDataOnce() {
        List<StockTable> stockTables = this.stockTableDao.queryAll(new StockTable());
        Date startOfDay = DateUtils.getStartOfDay(new Date());
        List<String> stringList = stockTables.stream()
                .filter(a -> a.getStatus() != null && a.getStatus() == 1 && a.getDealDate().compareTo(startOfDay) > 0)
                .map(StockTable::getStockNum)
                .collect(Collectors.toList());
        if (! CollectionUtils.isEmpty(stringList)) {
            stringList = stringList;
        }else {
            stringList = stockTables.stream().map(StockTable::getStockNum)
                    .collect(Collectors.toList());
        }
        LOGGER.info("synAllDataOnce-->nowInUseList:" + stringList.size());
        loopGetInfo(stringList);
    }

    @Override
    public List<StockMainTable> getData() {
        StockMainTable stockMainTable = new StockMainTable();
        List<StockMainTable> stockMainTableList = this.stockMainTableMapper.queryAllByLimit(1, 100);
        return stockMainTableList;
    }

    private void loopGetInfo(List<String> nowInUseList) {
        for (String stockNum : nowInUseList) {
           /* try {
                TimeUnit.NANOSECONDS.sleep(50);
            } catch (InterruptedException e) {
                LOGGER.error("sleep:error", e);
            }*/
            this.runTask(stockNum);
        }
    }

    /**
     * 将 000001 - 1000000之前的所有数据进行同步
     */
    private void loopGetInfo() {
        //上证范围
//        for (int i = 600000; i < 609000; i++) {
//            String stockNum = i + "";
//            this.runTask(stockNum);
//        }
//        //创业板范围，后续可能需要加大
//        for (int i = 300000; i < 302999; i++) {
//            String stockNum = i + "";
//            this.runTask(stockNum);
//        }
//        for (int i = 1600; i < 2999; i++) {
//            String stockNum = "00" + i;
//            this.runTask(stockNum);
//        }
        for (int i = 100000; i < 1000000; i++) {
            String stockNum = "" + i;
            this.runTask(stockNum);
        }
        for (int i = 10000; i < 100000; i++) {
            String stockNum = "0" + i;
            this.runTask(stockNum);
        }
        for (int i = 1000; i < 10000; i++) {
            String stockNum = "00" + i;
            this.runTask(stockNum);
        }
        for (int i = 100; i < 1000; i++) {
            String stockNum = "000" + i;
            this.runTask(stockNum);
        }
        for (int i = 10; i < 100; i++) {
            String stockNum = "0000" + i;
            this.runTask(stockNum);
        }
        for (int i = 1; i < 10; i++) {
            String stockNum = "00000" + i;
            this.runTask(stockNum);
        }
    }

    private void runTask(String stockNum) {
        if (!nowInUseList.contains(stockNum)) {
            nowInUseList.add(stockNum);
        }
        threadPoolExecutor.execute(() -> {
            try {
                this.doAssableInsertTable(stockNum);
            } catch (Exception e) {
                LOGGER.error("stocknum = [{}]", stockNum, e);
            } catch (Error e) {
                LOGGER.error("stocknum = [{}]", stockNum, e);
            }

        });
    }

    /**
     * 处理单个代码 如果存在则进行更新
     *
     * @param stockNum
     */
    private void doAssableInsertTable(String stockNum) {
        String url = "http://hq.sinajs.cn/list=sh" + stockNum;
        long urlStart = System.currentTimeMillis();
        String s = HttpUtils.httpGet(url);
        //var hq_str_sh601006="大秦铁路,6.070,6.040,6.010,6.100,6.000,6.010,6.020,38456996,232152112.000,
        // 1038213,6.010,2976128,6.000,871500,5.990,977700,5.980,220400,5.970,27200,6.020,630800,6.030,
        // 716475,6.040,272600,6.050,207600,6.060,2021-07-12,15:00:00,00,";

        //System.out.println(s);
        String[] split = s.split(",");
        if (split.length < 25) {
            if (Integer.valueOf(stockNum) % 19 == 0) {
                LOGGER.info("stockNum:[{}],ret:[{}],cost:[{}]", stockNum, s, (System.currentTimeMillis() - urlStart));
            }
            //nowInLowList.add(stockNum);
            return;
        }
        StockTable stockTable = this.getStockTable(stockNum, split);
        StockTable table = new StockTable();
        table.setStockNum(stockNum);
        Date date = Calendar.getInstance().getTime();
        List<StockTable> stockTables = this.stockTableDao.queryAll(table);
        if (CollectionUtils.isEmpty(stockTables)) {
            stockTable.setCreateTime(date);
            stockTable.setUpdateTime(date);
            this.stockTableDao.insert(stockTable);
        } else {
            stockTable.setUpdateTime(date);
            this.stockTableDao.update(stockTable);
        }
    }

    private StockTable getStockTable(String stockNum, String[] split) {
        StockTable stockTable = new StockTable();
//        stockTable.setId(0);
        stockTable.setStockName(split[0]);
        stockTable.setStockNum(stockNum);
        stockTable.setBeginPrice(split[1]);
        stockTable.setLastDayEndPrice(split[2]);
        stockTable.setCurrPrice(split[3]);
        stockTable.setDayMaxPrice(split[4]);
        stockTable.setDayMinPrice(split[5]);
        stockTable.setBuyOne(split[6]);
        stockTable.setSellOne(split[7]);
        stockTable.setSellDoneNum(split[8]);
        stockTable.setSellDoneMon(split[9]);
        stockTable.setBuyOneNum(split[10]);
        stockTable.setBuyOneMon(split[11]);
        stockTable.setBuyTwoNum(split[12]);
        stockTable.setBuyTwoMon(split[13]);
        stockTable.setBuyThreeNum(split[14]);
        stockTable.setBuyThreeMon(split[15]);
        stockTable.setBuyForeNum(split[16]);
        stockTable.setBuyForeMon(split[17]);
        stockTable.setBuyFiveNum(split[18]);
        stockTable.setBuyFiveMon(split[19]);
        stockTable.setSellOneNum(split[20]);
        stockTable.setSellOneMon(split[21]);
        stockTable.setSellTwoNum(split[22]);
        stockTable.setSellTwoMon(split[23]);
        stockTable.setSellThreeNum(split[24]);
        stockTable.setSellThreeMon(split[25]);
        stockTable.setSellForeNum(split[26]);
        stockTable.setSellForeMon(split[27]);
        stockTable.setSellFiveNum(split[28]);
        stockTable.setSellFiveMon(split[29]);
        if (StringUtils.isEmpty(split[31])) {
            String date = split[30];
            Date parseDate = DateUtils.parseDate(date, DateUtils.yyyy_MM_dd);
            stockTable.setDealDate(parseDate);
        } else {
            String date = split[30] + " " + split[31] + "." + split[32];
            Date parseDate = DateUtils.parseDate(date, DateUtils.yyyy_MM_dd_hh_mm_ss);
            stockTable.setDealDate(parseDate);
        }
        stockTable.setStatus(0);
        //买一的价格 或者当前价格大于1的时候认为这条数据是有效的
        if (new BigDecimal(stockTable.getBuyOneMon()).compareTo(BigDecimal.ONE) > 0
                || new BigDecimal(stockTable.getCurrPrice()).compareTo(BigDecimal.ONE) > 0) {
            stockTable.setStatus(1);
        }
        return stockTable;
    }
}