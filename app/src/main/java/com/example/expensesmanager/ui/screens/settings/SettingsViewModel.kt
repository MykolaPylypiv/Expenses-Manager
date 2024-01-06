package com.example.expensesmanager.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesmanager.data.repository.OperationRepository
import com.example.expensesmanager.data.store.StoreSettings
import com.example.expensesmanager.domain.model.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val operationRepository: OperationRepository, private val storeSettings: StoreSettings
) : ViewModel() {

    val settings = storeSettings.get()

    fun deleteAll() {
        viewModelScope.launch {
            operationRepository.deleteAll()
        }
    }

    fun saveSettings(settings: Settings) = viewModelScope.launch {
        storeSettings.save(settings)
    }

    fun search(value: String): List<String> {
        val newList = mutableListOf<String>()
        currencyList.map { currency ->
            if (currency.lowercase().contains(value.lowercase())) newList.add(currency)
        }

        return newList.toList()
    }

    private val currencyList = listOf(
        " Австралія Австралійський долар $ AUD 036 ",
        " Австрія Євро € EUR 978 ",
        " Азербайджан Азербайджанський манат ₼ AZN 944 ",
        " Албанія Лек L ALL 008",
        " Алжир Алжирський динар .د.ج • DA DZD 012",
        " Американське Самоа Долар США $ USD 840 ",
        " Ангілья Східнокарибський долар $ XCD 951 ",
        " Ангола Кванза Kz AOA 973 ",
        " Андорра Євро € EUR 978 ",
        " Антигуа і Барбуда Східнокарибський долар $ XCD 951 ",
        " Аргентина Аргентинський песо $ ARS 032",
        " Аруба Арубський флорин ƒ AWG 533",
        " Афганістан Афгані ؋ • Af AFN 971",
        " Багамські О-ви Багамський долар $ BSD 044",
        " Бангладеш Така ৳ BDT 050",
        " Барбадос Барбадоський долар $ BBD 052",
        " Бахрейн Бахрейнський динар .د.ب • BD BHD 048",
        " Беліз Белізький долар $ BZD 084",
        " Бельгія Євро € EUR 978",
        " Бенін Західноафриканський франк ₣ XOF 952",
        " Бермудські О-ви Бермудський долар $ BMD 060",
        " Болгарія Болгарський лев лв BGN 975",
        " Болівія Болівіано $ BOB 068",
        " Боснія і Герцеговина Конвертовна марка KM BAM 977",
        " Ботсвана Пула P BWP 072",
        " Бразилія Бразильський реал $ BRL 986",
        " Бруней Брунейський долар $ BND 096",
        " Буркіна-Фасо Західноафриканський франк ₣ XOF 952",
        " Бурунді Бурундійський франк ₣ BIF 108",
        " Бутан Нгултрум Nu BTN 064",
        " Вануату Вату Vt VUV 548",
        " Ватикан Євро € EUR 978",
        " Великобританія Фунт стерлінгів £ GBP 826",
        " Венесуела Суверенний болівар Bs. S. VES 928",
        " В’єтнам Донг ₫ VND 704",
        " Вірменія Вірменський драм ֏ AMD 051",
        " Волліс і Футуна Франк КФП ₣ XPF 953",
        " Габон Центральноафриканський франк ₣ XAF 950",
        " Гаїті Гурд G HTG 332",
        " Гамбія Даласі D GMD 270",
        " Гана Ганський седі ₵ GHS 936",
        " Гаяна Гаянський долар $ GYD 328",
        " Гваделупа Євро € EUR 978",
        " Гватемала Кетсаль Q GTQ 320",
        " Гвінея Гвінейський франк ₣ GNF 324",
        " Гвінея-Бісау Західноафриканський франк ₣ XOF 952",
        " Гібралтар Гібралтарський фунт £ GIP 292",
        " Гондурас Лемпіра L HNL 340",
        " Гонконг Гонконгський долар $ HKD 344",
        " Гренада Східнокарибський долар $ XCD 951",
        " Гренландія Данська крона kr DKK 208",
        " Греція Євро € EUR 978",
        " Грузія Ларі ₾ GEL 981",
        " Гуам Долар США $ USD 840",
        " Данія Данська крона kr DKK 208",
        " Демократична Республіка Конго Конголезький франк ₣ CDF 976",
        " Джерсі Фунт стерлінгів £ GBP 826",
        " Джибуті Франк Джибуті ₣ DJF 262",
        " Домініка Східнокарибський долар $ XCD 951",
        " Домініканська Республіка Домініканський песо $ DOP 214",
        " Еквадор Долар США $ USD 840",
        " Еритрея Накфа Nfk ERN 232",
        " Есватіні Ліланґені L SZL 748",
        " Естонія Євро € EUR 978",
        " Ефіопія Ефіопський бир Br ETB 230",
        " Єгипет Єгипетський фунт .ج.م • LE EGP 818",
        " Ємен Єменський ріал ﷼ • YR YER 886",
        " Замбія Замбійська квача K ZMW 967",
        " Західна Сахара Марокканський дирхам .د.م • Dh MAD 504",
        " Зімбабве Зімбабвійський долар Z$ ZWL 932",
        " Ізраїль Новий ізраїльський шекель ₪ ILS 376",
        " Індія Індійська рупія ₹ INR 356",
        " Індонезія Індонезійська рупія Rp IDR 360",
        " Ірак Іракський динар .د.ع • ID IQD 368",
        " Іран Іранський ріал ﷼ • IR IRR 364",
        " Ірландія Євро € EUR 978",
        " Ісландія Ісландська крона kr ISK 352",
        " Іспанія Євро € EUR 978",
        " Італія Євро € EUR 978",
        " Йорданія Йорданський динар .د.إ • JD JOD 400",
        " Кабо-Верде Ескудо Кабо-Верде $ CVE 132",
        " Казахстан Теньге ₸ KZT 398",
        " Кайманові О-ви Долар Кайманових О-в $ KYD 136",
        " Камбоджа Рієль ៛ KHR 116",
        " Камерун Центральноафриканський франк ₣ XAF 950",
        " Канада Канадський долар $ CAD 124",
        " Катар Катарський ріал ﷼ • QR QAR 634",
        " Кенія Кенійський шилінг KSh KES 404",
        " Киргизстан Сом с KGS 417",
        " Китай Юань ¥ CNY 156",
        " Кіпр Євро € EUR 978",
        " Кірибаті Австралійський долар $ AUD 036",
        " КНДР Північнокорейська вона 원 KPW 408",
        " Кокосові О-ви Австралійський долар $ AUD 036",
        " Колумбія Колумбійський песо $ COP 170",
        " Коморcькі О-ви Коморський франк ₣ KMF 174",
        " Корея Південнокорейська вона ₩ KRW 410",
        " Коста-Рика Костариканський колон ₡ CRC 188",
        " Кот-д Івуар Західноафриканський франк ₣ XOF 952",
        " Куба Кубинський песо $ CUP 192",
        " Кувейт Кувейтський динар .د.ك • KD KWD 414",
        " Лаос Кіп ₭ LAK 418",
        " Латвія Євро € EUR 978",
        " Лесото Лоті L LSL 426",
        " Литва Євро € EUR 978",
        " Ліберія Ліберійський долар $ LRD 430",
        " Ліван Ліванський фунт .ل.ل LBP 422",
        " Лівія Лівійський динар .د.ل • LD LYD 434",
        " Ліхтенштейн Швейцарський франк ₣ CHF 756",
        " Люксембург Євро € EUR 978",
        " М’янма К’ят K MMK 104",
        " Маврикій Маврикійська рупія Re MUR 480",
        " Мавританія Угія UM MRU 929",
        " Мадагаскар Малагасійський аріарі Ar. MGA 969",
        " Майотта Євро € EUR 978",
        " Макао Патака $ MOP 446",
        " Македонія Денар ден. MKD 807",
        " Малаві Малавійська квача K MWK 454",
        " Малайзія Малайзійський рингіт RM MYR 458",
        " Малі Західноафриканський франк ₣ XOF 952",
        " Мальдіви Руфія .ރ • Rf MVR 462",
        " Мальта Євро € EUR 978",
        " Марокко Марокканский дирхам .د.م • Dh MAD 504",
        " Мартиніка Євро € EUR 978",
        " Маршаллові О-ви Долар США $ USD 840",
        " Мексика Мексиканський песо $ MXN 484",
        " Мікронезія Долар США $ USD 840",
        " Мозамбік Мозамбіцький метікал MT MZN 943",
        " Молдова Молдовський лей L MDL 498",
        " Монако Євро € EUR 978",
        " Монголія Тугрик ₮ MNT 496",
        " Монтсеррат Східнокарибський долар $ XCD 951",
        " Намібія Намібійський долар N$ NAD 516",
        " Науру Австралійський долар $ AUD 036",
        " Непал Непальська рупія Re NPR 524",
        " Нігер Західноафриканський франк ₣ XOF 952",
        " Нігерія Найра ₦ NGN 566",
        " Нідерланди Євро € EUR 978",
        " Нікарагуа Нікарагуанська кордоба $ NIO 558",
        " Німеччина Євро € EUR 978",
        " Ніуе Новозеландський долар $ NZD 554",
        " Нова Зеландія Новозеландський долар $ NZD 554",
        " Нова Каледонія Франк КФП ₣ XPF 953",
        " Норвегія Норвезька крона kr NOK 578",
        " ОАЕ Дирхам ОАЕ .د.إ • Dh AED 784",
        " Оман Оманський ріал ﷼ • RO OMR 512",
        " О-в Мен Фунт стерлінгів £ GBP 826",
        " О-в Норфолк Австралійський долар $ AUD 036",
        " О-в Різдва Австралійський долар $ AUD 036",
        " О-ви Кука Новозеландський долар $ NZD 554",
        " О-ви Теркс і Кайкос Долар США $ USD 840",
        " Пакистан Пакистанська рупія Re PKR 586",
        " Палау Долар США $ USD 840",
        " Панама Бальбоа B/. PAB 590",
        " Папуа – Нова Гвінея Кіна K PGK 598",
        " Парагвай Гуарані ₲ PYG 600",
        " Перу Соль S/ PEN 604",
        " Південна Африка Ранд R ZAR 710",
        " Південний Судан Південносуданський фунт SSP SSP 728",
        " Північні Маріанські О-ви Долар США $ USD 840",
        " Польща Злотий zł PLN 985",
        " Португалія Євро € EUR 978",
        " Пуерто-Рико Долар США $ USD 840",
        " Республіка Конго Центральноафриканський франк ₣ XAF 950",
        " Реюньйон Євро € EUR 978",
        " Руанда Руандійський франк ₣ RWF 646",
        " Румунія Румунський лей L RON 946",
        " Сальвадор Сальвадорський колон ₡ SVC 222",
        " Самоа Тала $ WST 882",
        " Сан-Марино Євро € EUR 978",
        " Сан-Томе і Принсіпі Добра Db STN 930",
        " Саудівська Аравія Саудівський ріал ﷼ • SR SAR 682",
        " Сейшельські О-ви Сейшельська рупія Re SCR 690",
        " Сен-Бартельмі Євро € EUR 978",
        " Сен-Мартен Євро € EUR 978",
        " Сен-П’єр і Мікелон Євро € EUR 978",
        " Сенегал Західноафриканський франк ₣ XOF 952",
        " Сент-Вінсент і Гренадини Східнокарибський долар $ XCD 951",
        " Сент-Кіттс і Невіс Східнокарибський долар $ XCD 951",
        " Сент-Люсія Східнокарибський долар $ XCD 951",
        " Сербія Сербський динар din. • дин. RSD 941",
        " Сингапур Сингапурський долар $ SGD 702",
        " Сирія Сирійський фунт .ل.س • S£ SYP 760",
        " Сінт-Естатіус Долар США $ USD 840",
        " Сінт-Мартен Нідерландський антильський гульден ƒ ANG 532",
        " Словаччина Євро € EUR 978",
        " Словенія Євро € EUR 978",
        " Соломонові О-ви Долар Соломонових О-в $ SBD 090",
        " Сомалі Сомалійський шилінг S SOS 706",
        " Судан Суданський фунт £ SDG 938",
        " Суринам Суринамський долар $ SRD 968",
        " США Долар США $ USD 840",
        " Сьєрра-Леоне Леоне Le SLL 694",
        " Таджикистан Сомоні с. TJS 972",
        " Таїланд Бат ฿ THB 764",
        " Тайвань Новий тайванський долар NT$ TWD 901",
        " Танзанія Танзанійський шилінг TSh TZS 834",
        " Тимор-Лешті Долар США $ USD 840",
        " Того Західноафриканський франк ₣ XOF 952",
        " Токелау Новозеландський долар $ NZD 554",
        " Тонга Паанга $ TOP 776",
        " Тринідад і Тобаго Долар Тринідаду і Тобаго $ TTD 780",
        " Тувалу Австралійський долар $ AUD 036",
        " Туніс Туніський динар .د.ت • TD TND 788",
        " Туреччина Турецька ліра ₺ TRY 949",
        " Туркменістан Новий туркменський манат m TMT 934",
        " Уганда Угандійський шилінг USh UGX 800",
        " Угорщина Форинт Ft HUF 348",
        " Узбекистан Узбецький сум so’m • сўм UZS 860",
        " Україна Гривня ₴ UAH 980",
        " Уругвай Уругвайський песо $ UYU 858",
        " Фарерські О-ви Данська крона kr DKK 208",
        " Фіджі Фіджійський долар $ FJD 242",
        " Філіппіни Філіппінський песо ₱ PHP 608",
        " Фінляндія Євро € EUR 978",
        " Франція Євро € EUR 978",
        " Французька Полінезія Євро € EUR 953",
        " Хорватія Хорватська куна Kn HRK 191",
        " Чад Центральноафриканський франк ₣ XAF 950",
        " Чехія Чеська крона Kč CZK 203",
        " Чилі Чилійський песо $ CLP 152",
        " Чорногорія Євро € EUR 978",
        " Швейцарія Швейцарський франк ₣ CHF 756",
        " Швеція Шведська крона kr SEK 752",
        " Шрі-Ланка Рупія Шрі-Ланки Re LKR 144",
        " Ямайка Ямайський долар $ JMD 388",
        " Японія Єна ¥ JPY 392"
    )
}