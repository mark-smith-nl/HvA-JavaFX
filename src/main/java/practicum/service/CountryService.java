package practicum.service;

import practicum.models.Country;

import java.util.ArrayList;
import java.util.List;
import practicum.dao.CountryPersistentDao;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class CountryService extends AbstractService<Country> {

    public CountryService() {
        super(new CountryPersistentDao());
    }

    public List<Country> getAll() {
        return persistentDao.getAll();
    }

    @Override
    public void update(Country entity) {
        persistentDao.update(entity);
    }

    public List<Country> getAll2() {

        List<Country> countries = new ArrayList<>();
        /*countries.add(new Country(1, "AF", "Afghanistan"));
        countries.add(new Country(10, "AG", "Antigua and Barbuda"));
        countries.add(new Country(100, "IS", "Iceland"));
        countries.add(new Country(101, "IN", "India"));
        countries.add(new Country(102, "ID", "Indonesia"));
        countries.add(new Country(103, "IR", "Iran, Islamic Republic Of"));
        countries.add(new Country(104, "IQ", "Iraq"));
        countries.add(new Country(105, "IE", "Ireland"));
        countries.add(new Country(106, "IM", "Isle of Man"));*/
        /*countries.add(new Country(107, "IL", "Israel"));
        countries.add(new Country(108, "IT", "Italy"));
        countries.add(new Country(109, "JM", "Jamaica"));
        countries.add(new Country(11, "AR", "Argentina"));
        countries.add(new Country(110, "JP", "Japan"));
        countries.add(new Country(111, "JE", "Jersey"));
        countries.add(new Country(112, "JO", "Jordan"));
        countries.add(new Country(113, "KZ", "Kazakhstan"));
        countries.add(new Country(114, "KE", "Kenya"));
        countries.add(new Country(115, "KI", "Kiribati"));
        countries.add(new Country(116, "KP", "Korea, Democratic People'S Republic of"));
        countries.add(new Country(117, "KR", "Korea, Republic of"));
        countries.add(new Country(118, "KW", "Kuwait"));
        countries.add(new Country(119, "KG", "Kyrgyzstan"));
        countries.add(new Country(12, "AM", "Armenia"));
        countries.add(new Country(120, "LA", "Lao People'S Democratic Republic"));
        countries.add(new Country(121, "LV", "Latvia"));
        countries.add(new Country(122, "LB", "Lebanon"));
        countries.add(new Country(123, "LS", "Lesotho"));
        countries.add(new Country(124, "LR", "Liberia"));
        countries.add(new Country(125, "LY", "Libyan Arab Jamahiriya"));
        countries.add(new Country(126, "LI", "Liechtenstein"));
        countries.add(new Country(127, "LT", "Lithuania"));
        countries.add(new Country(128, "LU", "Luxembourg"));
        countries.add(new Country(129, "MO", "Macao"));
        countries.add(new Country(13, "AW", "Aruba"));
        countries.add(new Country(130, "MK", "Macedonia, The Former Yugoslav Republic of"));
        countries.add(new Country(131, "MG", "Madagascar"));
        countries.add(new Country(132, "MW", "Malawi"));
        countries.add(new Country(133, "MY", "Malaysia"));
        countries.add(new Country(134, "MV", "Maldives"));
        countries.add(new Country(135, "ML", "Mali"));
        countries.add(new Country(136, "MT", "Malta"));
        countries.add(new Country(137, "MH", "Marshall Islands"));
        countries.add(new Country(138, "MQ", "Martinique"));
        countries.add(new Country(139, "MR", "Mauritania"));
        countries.add(new Country(14, "AU", "Australia"));
        countries.add(new Country(140, "MU", "Mauritius"));
        countries.add(new Country(141, "YT", "Mayotte"));
        countries.add(new Country(142, "MX", "Mexico"));
        countries.add(new Country(143, "FM", "Micronesia, Federated States of"));
        countries.add(new Country(144, "MD", "Moldova, Republic of"));
        countries.add(new Country(145, "MC", "Monaco"));
        countries.add(new Country(146, "MN", "Mongolia"));
        countries.add(new Country(147, "MS", "Montserrat"));
        countries.add(new Country(148, "MA", "Morocco"));
        countries.add(new Country(149, "MZ", "Mozambique"));
        countries.add(new Country(15, "AT", "Austria"));
        countries.add(new Country(150, "MM", "Myanmar"));
        countries.add(new Country(151, "NA", "Namibia"));
        countries.add(new Country(152, "NR", "Nauru"));
        countries.add(new Country(153, "NP", "Nepal"));
        countries.add(new Country(154, "NL", "Netherlands"));
        countries.add(new Country(155, "AN", "Netherlands Antilles"));
        countries.add(new Country(156, "NC", "New Caledonia"));
        countries.add(new Country(157, "NZ", "New Zealand"));
        countries.add(new Country(158, "NI", "Nicaragua"));
        countries.add(new Country(159, "NE", "Niger"));
        countries.add(new Country(16, "AZ", "Azerbaijan"));
        countries.add(new Country(160, "NG", "Nigeria"));
        countries.add(new Country(161, "NU", "Niue"));
        countries.add(new Country(162, "NF", "Norfolk Island"));
        countries.add(new Country(163, "MP", "Northern Mariana Islands"));
        countries.add(new Country(164, "NO", "Norway"));
        countries.add(new Country(165, "OM", "Oman"));
        countries.add(new Country(166, "PK", "Pakistan"));
        countries.add(new Country(167, "PW", "Palau"));
        countries.add(new Country(168, "PS", "Palestinian Territory, Occupied"));
        countries.add(new Country(169, "PA", "Panama"));
        countries.add(new Country(17, "BS", "Bahamas"));
        countries.add(new Country(170, "PG", "Papua New Guinea"));
        countries.add(new Country(171, "PY", "Paraguay"));
        countries.add(new Country(172, "PE", "Peru"));
        countries.add(new Country(173, "PH", "Philippines"));
        countries.add(new Country(174, "PN", "Pitcairn"));
        countries.add(new Country(175, "PL", "Poland"));
        countries.add(new Country(176, "PT", "Portugal"));
        countries.add(new Country(177, "PR", "Puerto Rico"));
        countries.add(new Country(178, "QA", "Qatar"));
        countries.add(new Country(179, "RE", "Reunion"));
        countries.add(new Country(18, "BH", "Bahrain"));
        countries.add(new Country(180, "RO", "Romania"));
        countries.add(new Country(181, "RU", "Russian Federation"));
        countries.add(new Country(182, "RW", "RWANDA"));
        countries.add(new Country(183, "SH", "Saint Helena"));
        countries.add(new Country(184, "KN", "Saint Kitts and Nevis"));
        countries.add(new Country(185, "LC", "Saint Lucia"));
        countries.add(new Country(186, "PM", "Saint Pierre and Miquelon"));
        countries.add(new Country(187, "VC", "Saint Vincent and the Grenadines"));
        countries.add(new Country(188, "WS", "Samoa"));
        countries.add(new Country(189, "SM", "San Marino"));
        countries.add(new Country(19, "BD", "Bangladesh"));
        countries.add(new Country(190, "ST", "Sao Tome and Principe"));
        countries.add(new Country(191, "SA", "Saudi Arabia"));
        countries.add(new Country(192, "SN", "Senegal"));
        countries.add(new Country(193, "CS", "Serbia and Montenegro"));
        countries.add(new Country(194, "SC", "Seychelles"));
        countries.add(new Country(195, "SL", "Sierra Leone"));
        countries.add(new Country(196, "SG", "Singapore"));
        countries.add(new Country(197, "SK", "Slovakia"));
        countries.add(new Country(198, "SI", "Slovenia"));
        countries.add(new Country(199, "SB", "Solomon Islands"));
        countries.add(new Country(2, "AX", "Åland Islands"));
        countries.add(new Country(20, "BB", "Barbados"));
        countries.add(new Country(200, "SO", "Somalia"));
        countries.add(new Country(201, "ZA", "South Africa"));
        countries.add(new Country(202, "GS", "South Georgia and the South Sandwich Islands"));
        countries.add(new Country(203, "ES", "Spain"));
        countries.add(new Country(204, "LK", "Sri Lanka"));
        countries.add(new Country(205, "SD", "Sudan"));
        countries.add(new Country(206, "SR", "Suriname"));
        countries.add(new Country(207, "SJ", "Svalbard and Jan Mayen"));
        countries.add(new Country(208, "SZ", "Swaziland"));
        countries.add(new Country(209, "SE", "Sweden"));
        countries.add(new Country(21, "BY", "Belarus"));
        countries.add(new Country(210, "CH", "Switzerland"));
        countries.add(new Country(211, "SY", "Syrian Arab Republic"));
        countries.add(new Country(212, "TW", "Taiwan, Province of China"));
        countries.add(new Country(213, "TJ", "Tajikistan"));
        countries.add(new Country(214, "TZ", "Tanzania, United Republic of"));
        countries.add(new Country(215, "TH", "Thailand"));
        countries.add(new Country(216, "TL", "Timor-Leste"));
        countries.add(new Country(217, "TG", "Togo"));
        countries.add(new Country(218, "TK", "Tokelau"));
        countries.add(new Country(219, "TO", "Tonga"));
        countries.add(new Country(22, "BE", "Belgium"));
        countries.add(new Country(220, "TT", "Trinidad and Tobago"));
        countries.add(new Country(221, "TN", "Tunisia"));
        countries.add(new Country(222, "TR", "Turkey"));
        countries.add(new Country(223, "TM", "Turkmenistan"));
        countries.add(new Country(224, "TC", "Turks and Caicos Islands"));
        countries.add(new Country(225, "TV", "Tuvalu"));
        countries.add(new Country(226, "UG", "Uganda"));
        countries.add(new Country(227, "UA", "Ukraine"));
        countries.add(new Country(228, "AE", "United Arab Emirates"));
        countries.add(new Country(229, "GB", "United Kingdom"));
        countries.add(new Country(23, "BZ", "Belize"));
        countries.add(new Country(230, "US", "United States"));
        countries.add(new Country(231, "UM", "United States Minor Outlying Islands"));
        countries.add(new Country(232, "UY", "Uruguay"));
        countries.add(new Country(233, "UZ", "Uzbekistan"));
        countries.add(new Country(234, "VU", "Vanuatu"));
        countries.add(new Country(235, "VE", "Venezuela"));
        countries.add(new Country(236, "VN", "Viet Nam"));
        countries.add(new Country(237, "VG", "Virgin Islands, British"));
        countries.add(new Country(238, "VI", "Virgin Islands, U.S."));
        countries.add(new Country(239, "WF", "Wallis and Futuna"));
        countries.add(new Country(24, "BJ", "Benin"));
        countries.add(new Country(240, "EH", "Western Sahara"));
        countries.add(new Country(241, "YE", "Yemen"));
        countries.add(new Country(242, "ZM", "Zambia"));
        countries.add(new Country(243, "ZW", "Zimbabwe"));
        countries.add(new Country(247, "YY", "Ytrium"));
        countries.add(new Country(25, "BM", "Bermuda"));
        countries.add(new Country(250, "XX", "Xantippe"));
        countries.add(new Country(26, "BT", "Bhutan"));
        countries.add(new Country(27, "BO", "Bolivia"));
        countries.add(new Country(28, "BA", "Bosnia and Herzegovina"));
        countries.add(new Country(29, "BW", "Botswana"));
        countries.add(new Country(3, "AL", "Albania"));
        countries.add(new Country(30, "BV", "Bouvet Island"));
        countries.add(new Country(31, "BR", "Brazil"));
        countries.add(new Country(32, "IO", "British Indian Ocean Territory"));
        countries.add(new Country(33, "BN", "Brunei Darussalam"));
        countries.add(new Country(34, "BG", "Bulgaria"));
        countries.add(new Country(35, "BF", "Burkina Faso"));
        countries.add(new Country(36, "BI", "Burundi"));
        countries.add(new Country(37, "KH", "Cambodia"));
        countries.add(new Country(38, "CM", "Cameroon"));
        countries.add(new Country(39, "CA", "Canada"));
        countries.add(new Country(4, "DZ", "Algeria"));
        countries.add(new Country(40, "CV", "Cape Verde"));
        countries.add(new Country(41, "KY", "Cayman Islands"));
        countries.add(new Country(42, "CF", "Central African Republic"));
        countries.add(new Country(43, "TD", "Chad"));
        countries.add(new Country(44, "CL", "Chile"));
        countries.add(new Country(45, "CN", "China"));
        countries.add(new Country(46, "CX", "Christmas Island"));
        countries.add(new Country(47, "CC", "Cocos (Keeling) Islands"));
        countries.add(new Country(48, "CO", "Colombia"));
        countries.add(new Country(49, "KM", "Comoros"));
        countries.add(new Country(5, "AS", "American Samoa"));
        countries.add(new Country(50, "CG", "Congo"));
        countries.add(new Country(51, "CD", "Congo, The Democratic Republic of the"));
        countries.add(new Country(52, "CK", "Cook Islands"));
        countries.add(new Country(53, "CR", "Costa Rica"));
        countries.add(new Country(54, "CI", "Cote D'Ivoire"));
        countries.add(new Country(55, "HR", "Croatia"));
        countries.add(new Country(56, "CU", "Cuba"));
        countries.add(new Country(57, "CY", "Cyprus"));
        countries.add(new Country(58, "CZ", "Czech Republic"));
        countries.add(new Country(59, "DK", "Denmark"));
        countries.add(new Country(6, "AD", "AndorrA"));
        countries.add(new Country(60, "DJ", "Djibouti"));
        countries.add(new Country(61, "DM", "Dominica"));
        countries.add(new Country(62, "DO", "Dominican Republic"));
        countries.add(new Country(63, "EC", "Ecuador"));
        countries.add(new Country(64, "EG", "Egypt"));
        countries.add(new Country(65, "SV", "El Salvador"));
        countries.add(new Country(66, "GQ", "Equatorial Guinea"));
        countries.add(new Country(67, "ER", "Eritrea"));
        countries.add(new Country(68, "EE", "Estonia"));
        countries.add(new Country(69, "ET", "Ethiopia"));
        countries.add(new Country(7, "AO", "Angola"));
        countries.add(new Country(70, "FK", "Falkland Islands (Malvinas)"));
        countries.add(new Country(71, "FO", "Faroe Islands"));
        countries.add(new Country(72, "FJ", "Fiji"));
        countries.add(new Country(73, "FI", "Finland"));
        countries.add(new Country(74, "FR", "France"));
        countries.add(new Country(75, "GF", "French Guiana"));
        countries.add(new Country(76, "PF", "French Polynesia"));
        countries.add(new Country(77, "TF", "French Southern Territories"));
        countries.add(new Country(78, "GA", "Gabon"));
        countries.add(new Country(79, "GM", "Gambia"));
        countries.add(new Country(8, "AI", "Anguilla"));
        countries.add(new Country(80, "GE", "Georgia"));
        countries.add(new Country(81, "DE", "Germany"));
        countries.add(new Country(82, "GH", "Ghana"));
        countries.add(new Country(83, "GI", "Gibraltar"));
        countries.add(new Country(84, "GR", "Greece"));
        countries.add(new Country(85, "GL", "Greenland"));
        countries.add(new Country(86, "GD", "Grenada"));
        countries.add(new Country(87, "GP", "Guadeloupe"));
        countries.add(new Country(88, "GU", "Guam"));
        countries.add(new Country(89, "GT", "Guatemala"));
        countries.add(new Country(9, "AQ", "Antarctica"));
        countries.add(new Country(90, "GG", "Guernsey"));
        countries.add(new Country(91, "GN", "Guinea"));
        countries.add(new Country(92, "GW", "Guinea-Bissau"));
        countries.add(new Country(93, "GY", "Guyana"));
        countries.add(new Country(94, "HT", "Haiti"));
        countries.add(new Country(95, "HM", "Heard Island and Mcdonald Islands"));
        countries.add(new Country(96, "VA", "Holy See (Vatican City State)"));
        countries.add(new Country(97, "HN", "Honduras"));
        countries.add(new Country(98, "HK", "Hong Kong"));
        countries.add(new Country(99, "HU", "Hungary"));*/

        countries.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));

        return countries;
    }
}
