-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2024 at 09:38 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `do_an_cs3`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `active` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `menu_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `image`, `active`, `created_at`, `updated_at`, `menu_id`) VALUES
(2, 'Nữ cường', 'uploads/2024/04/19/nu_cuong.png', '1', '2024-04-17 11:54:01', '2024-04-19 02:08:16', 2),
(3, 'Lịch sử', 'uploads/2024/04/19/lich_su.png', '1', '2024-04-17 11:54:12', '2024-04-19 02:07:52', 2),
(4, 'Cổ tích', 'uploads/2024/04/19/co_tich.png', '1', '2024-04-17 11:54:20', '2024-04-19 02:04:34', 2),
(9, 'Tình cảm', 'uploads/2024/04/19/TongTai.png', '1', '2024-04-19 01:01:02', '2024-04-19 02:02:50', 2),
(11, 'Tu Tiên', 'uploads/2024/04/25/de_cu.png', '1', '2024-04-25 02:07:36', '2024-04-25 02:07:36', 1),
(17, 'Hoạt Hình', 'uploads/2024/06/11/HoatHinh.jpg', '1', '2024-06-11 00:19:44', '2024-06-11 00:19:44', 1);

-- --------------------------------------------------------

--
-- Table structure for table `chapters`
--

CREATE TABLE `chapters` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) NOT NULL,
  `manga_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chapters`
--

INSERT INTO `chapters` (`id`, `title`, `manga_id`, `created_at`, `updated_at`) VALUES
(4, 'Chương 1', 1, '2024-04-24 23:31:16', '2024-04-24 23:31:16'),
(5, 'Chương 2', 1, '2024-04-24 23:31:52', '2024-04-24 23:31:52'),
(7, 'Chapter 1', 16, '2024-05-10 02:13:04', '2024-05-10 02:13:04'),
(8, 'Chapter 2', 16, '2024-05-10 02:13:55', '2024-05-10 02:13:55'),
(9, 'Chapter 1', 17, '2024-06-09 02:58:57', '2024-06-09 02:58:57');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `comment` varchar(255) NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `manga_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `comment`, `user_id`, `manga_id`, `created_at`, `updated_at`) VALUES
(9, 'khi nào ra vậy ạ', 3, 5, '2024-04-25', '2024-04-25'),
(11, 'nhanh lên add ơiiii', 3, 11, '2024-04-25', '2024-04-25'),
(12, 'hi', 3, 5, '2024-04-25', '2024-04-25'),
(16, 'oki', 3, 5, '2024-04-25', '2024-04-25'),
(17, 'hello', 3, 5, '2024-04-25', '2024-04-25'),
(20, 'wow', 4, 1, '2024-04-25', '2024-04-25'),
(21, 'hello', 4, 1, '2024-04-25', '2024-04-25'),
(24, 'ok', 3, 1, '2024-06-07', '2024-06-07'),
(25, 'chaof', 2, 2, '2024-06-12', '2024-06-12'),
(28, 'Truyen Rat Hay ', 2, 5, '2024-06-12', '2024-06-12'),
(41, 'hello', 0, 9, '2024-06-16', '2024-06-16');

-- --------------------------------------------------------

--
-- Table structure for table `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `connection` text NOT NULL,
  `queue` text NOT NULL,
  `payload` longtext NOT NULL,
  `exception` longtext NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `links`
--

CREATE TABLE `links` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `content` text NOT NULL,
  `image` varchar(255) NOT NULL,
  `chapter_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `links`
--

INSERT INTO `links` (`id`, `content`, `image`, `chapter_id`, `created_at`, `updated_at`) VALUES
(7, 'Vào ngày phế hậu, Tạ Uyên bị ngã ngựa mất trí nhớ.1\r\n\r\nTrí nhớ của hắn dừng lại vào ba năm trước, thời điểm chúng ta yêu nhau nhất.\r\n\r\nHắn quên mất việc từng phạt ta quỳ dưới ánh mặt trời thiêu đốt chỉ để lấy lòng cô gái xuyên không.\r\n\r\nCũng quên mất việc đã lấy tính mệnh của toàn tộc ta ra áp chế, buộc ta phải vì cô gái xuyên không kia lấy thân thử thuốc.\r\n\r\nTrong lãnh cung.\r\n\r\nTạ Uyên giống như một đứa trẻ làm sai chuyện, che mặt khóc rống, hắn không muốn tin rằng mình đã làm ra những điều khốn nạn đó.\r\n\r\n\"Ninh Ninh, nàng đừng không quan tâm ta... cầu xin nàng... đừng rời bỏ ta...\"\r\n\r\nTa cũng quỳ xuống nói: \"Thần thiếp cùng bệ hạ, đường xuống Hoàng Tuyền, mong không gặp lại.”\r\n\r\nĐêm đó, trong cung mọi người đều biết, Tạ Uyên điên rồi.\r\n\r\n1.\r\n\r\nTa bị phế hậu.\r\n\r\nTừ trước đến nay trong cung luôn nâng cao đạp thấp, thái giám giọng the thé nói: “Nương nương, theo ý chỉ của hệ hạ, ngài không được phép mang theo bất cứ thứ gì, cho nên con súc sinh này ta sẽ mang đi.”\r\n\r\nChú chó nhỏ trong ngực cảm nhận được ác ý, toàn thân run rẩy.\r\n\r\nTa tháo chiếc trâm vàng trên đầu xuống, đưa cho hắn.\r\n\r\nTên thái giám đập đập nó vào tay rồi cười lớn, chế giễu ta là người hiểu chuyện.\r\n\r\n\"Thôi, một con súc sinh cũng không làm nên sóng gió gì, ngươi thích thì cữ giữ lại đi, nhưng nếu đồ sú/c sin/h này không an phận, ý đồ gây chuyện, đến lúc đó đừng trách ta ra tay không biết nặng nhẹ.”\r\n\r\nBọn họ rời đi, ngoài phòng vang lên tiếng khóa cửa, ở đây mỗi ngày ba bữa đều được đưa vào qua lỗ nhỏ bên kia cửa sổ.\r\n\r\nTrong lãnh cung không có than để sưởi ấm, gió bấc gào thét, lạnh thấu xương.\r\n\r\nTa lục trong ngăn tủ ra một chiếc chăn bông cũ nát đắp lên người.\r\n\r\nChó nhỏ cảm thấy không còn nguy hiểm, liền ngủ thiếp đi bên cạnh ta.\r\n\r\nMà lúc này trong Dưỡng Tâm điện, nguyên nhân chính là Hoàng Thượng đang hôn mê, làm cho lòng người bàng hoàng.\r\n\r\n2.\r\n\r\nKhông lâu sau, ma ma trưởng sự bên người Thái hậu truyền ta đến Dưỡng Tâm Điện.\r\n\r\nTrên đường, đi ngang qua Ngự Hoa Viên.\r\n\r\nNghe thấy các cung nữ đang tụ tập một chỗ kể về sự tình Bệ hạ vì cứu Lâm quý phi mà ngã ngựa.\r\n\r\n“Lúc đó đáng ra người bị thương chính là Lâm quý phi, nhưng bệ hạ đã đem người ôm vào ngực bảo vệ, mới khiến đầu đập vào đá. Nghe cung nhân hầu hạ hôm đó kể, trước khi bệ hạ hôn mê câu nói sau cùng là không cho phép trút giận lên quý phi.”\r\n\r\n\"Năm đó Lâm quý phi không rõ lai lịch xuất hiện trong cung, sau lưng không có mẫu tộc làm chỗ dựa lại có thể được bệ hạ ưu ái đến như vậy, thật khiến người ghen tị.”\r\n\r\n\"Có cái gì mà hâm mộ, gần vua như gần cọp, kết cục của phế hậu các ngươi không thấy sao.”\r\n\r\nTriệu ma ma tằng hắng một tiếng: “Các ngươi có bao nhiêu cái mạng mà dám nghị luận bệ hạ, cả đám đều ngại mình sống quá lâu rồi đúng không?”\r\n\r\nMấy người quay lại liền giật nảy mình, lập tức quỳ xuống đất cầu xin tha thứ: \"Triệu ma ma tha mạng, chúng nô tỳ cũng chỉ là nghe người khác nói như vậy.”\r\n\r\n\"Họa từ miệng mà ra, các ngươi cẩn thận cái miệng của mình đó, lần sau nếu để cho ta lại nghe thấy ai nghị luận về chủ tử, cái lưỡi của các ngươi cũng không cần dùng nữa.”\r\n\r\n“Vâng.” Mấy người hoảng sợ bỏ chạy.\r\n\r\nTriệu ma ma bỏ xuống thái độ nghiêm nghị vừa rồi, vừa đi vừa nói cùng ta: “Nương nương đừng nghe các nàng nói bậy, trong lòng bệ hạ vẫn còn có ngài.”\r\n\r\nTa bật cười trong lòng, trong lòng Tạ Uyên đâu còn vị trí của ta.\r\n\r\nKhi đến Dưỡng Tâm Điện, ta mới thực sự hiểu được ý tứ trong lời nói của Triệu ma ma.\r\n\r\nSau khi Tạ Uyên tỉnh lại sau cơn hôn mê, ký ức lại quay trở về ba năm trước đây, khi đó cô gái xuyên không, Lâm Thanh còn chưa xuất hiện, tình cảm của chúng ta vẫn còn rất ân ái.\r\n\r\nTa dừng ở trước cửa, thuận theo khe hở nhìn vào.\r\n\r\nTạ Uyên không ngừng nhìn về phía cửa tìm kiếm: “Ninh ninh thế nào còn chưa tới?”\r\n\r\nThái giám tâm phúc Từ Thịnh bên cạnh hắn khom người đáp lời: “Bệ hạ, Thái hậu nương nương đã phái người đi truyền phế hậu Cố thị rồi ạ.”\r\n\r\nTạ Uyên vừa mới tỉnh lại, sắc mặt vẫn có chút tái nhợt, vừa nghe đến hai chữ \"Phế Hậu\", ánh mắt lập tức trầm xuống: \"Phế Hậu cái gì?!\"\r\n\r\nTừ Thịnh liền kể cho Tạ Uyên nghe việc Cố thị trong ba năm nay đã bị thất sủng và bị phế hậu như thế nào.\r\n\r\nHắn tức giận, một chữ cũng không tin: \"Ninh Ninh đâu? Trẫm muốn đi tìm Ninh Ninh!\"\r\n\r\nHắn không quan tâm, ngay cả vớ giày cũng không mang liền muốn xông ra ngoài.\r\n\r\nLâm Thanh đi tới đỡ hắn, lại bị hắn chán ghét đẩy ra, giọng điệu sắc bén quát: \"Cách xa trẫm một chút!”\r\n\r\nLâm Thanh sửng sốt, có chút phát cáu lùi lại hai bước: “Bệ hạ từng nói, mặc dù không thể hứa hẹn một đời một thế một đôi, nhưng sẽ chỉ độc sủng duy nhất một mình ta.\"\r\n\r\nNghe vậy, Tạ Uyên cau mày: \"Làm càn! Lời nói lệch lạc như vậy mà ngươi cũng dám nói!”\r\n\r\nLâm Thanh không coi trọng: “Ta đã sớm nói với ngươi, nơi ta ở trước kia một vợ một chồng, không nạp thiếp là chuyện rất bình thường, ngươi cũng đã hứa với ta, sẽ cố gắng theo hướng này.”\r\n\r\nTa đứng ở cửa lắc đầu bật cười.\r\n\r\nLâm Thanh hoàn toàn không biết lời nói của mình sẽ đắc tội bao nhiêu người, có thể đến hầu hạ phần lớn đều là con gái hoặc tỷ muội của các đại thần trong triều, nếu thật sự muốn một vợ một chồng không có thiếp thất, thì đặt vị trí của các nàng đặt ở đâu.\r\n\r\nTa đặc biệt nhớ kỹ lần đầu khi Lâm Thanh nói những lời này, thực sự đã làm mọi người bị chấn kinh, không nói đế vương, ngay cả những công từ nhà bình thường nạp thiếp cũng là chuyện hết sức bình thường.\r\n\r\nMột đời một thế một đôi người, quả thực hoang đường.\r\n\r\nTạ Uyên quả nhiên nổi giận: \"Làm càn!\"\r\n\r\nLâm Thanh bị tiếng rống giận này dọa đến đỏ cả vành mắt, ba năm nay Tạ Uyên vẫn luôn dỗ dành, chiều chuộng nàng, đã bao giờ từng có thần sắc nghiêm nghị đến như vậy.\r\n\r\n3.\r\n\r\nKhi ta bước vào, hơn mười cặp mắt trong điện cùng lúc đổ dồn về phía ta.\r\n\r\nTa cúi người quỳ xuống đất, liền được Tạ Uyên ôm vào lòng, giọng điệu không còn lạnh lùng như trước, ngược lại có chút ủy khuất: \"Ninh Ninh, trẫm bị thương, sao nàng không tới gặp trẫm?\"\r\n\r\nTa im lặng không nói chuyện.\r\n\r\nTrong điện mọi người liếc nhìn nhau, Lâm quý phi hung hãn trừng mắt nhìn ta, bật khóc nức nở vô cùng đáng thương, chắc hẳn đang tự hỏi tại sao lại muốn đi cưỡi ngựa làm gì.\r\n\r\nAi mà không biết hiện giờ người Tạ Uyên chán ghét nhất chính là ta.\r\n\r\nNếu không phải vì mất trí nhớ, sợ rằng ngay cả gặp mặt cũng không muốn gặp, càng đừng nói đến chuyện hắn sẽ ôm ta.\r\n\r\n\"Ninh Ninh, bọn họ lừa gạt trẫm, nói trẫm phế bỏ hậu vị của nàng, điều này sao có thể?\"\r\n\r\nVẻ mặt ta lãnh đạm từ trong ngực hắn chui ra ngoài:\r\n\r\n“Bệ hạ, thánh chỉ phế hậu là chính ngài tự tay viết.”\r\n\r\n\"...\"\r\n\r\n\"Những lời bọn họ nói, đều là sự thật.\"\r\n\r\n4.\r\n\r\nGió lạnh thổi qua ngoài cửa sổ, bầu không khí trong điện rất nặng nề.\r\n\r\nTạ Uyên ánh mắt có chút tối sầm, hắn thông minh như vậy, cho dù ngoài miệng nói không tin, nhưng trong lòng hắn biết những chuyện này thật sự đã xảy ra, hắn không rõ, một đôi vợ chồng trẻ làm thế nào có thể dẫn đến tình trạng chán ghét nhau đến như vậy.\r\n\r\nLâm Thanh không giữ được bình tĩnh, muốn dùng chuyện cũ kích thích Tạ Uyên khôi phục trí nhớ: “Bệ hạ, ngài bây giờ khác với ba năm trước, bây giờ ngài tôn trọng nam nữ bình đẳng.”', '', 4, '2024-04-24 23:31:16', '2024-06-09 22:37:29'),
(8, 'Trừ phi triều đại bị lật đổ, nếu không nào có ai dám đem đồ vật trong cung hoàng hậu đi bán, làm vậy thì đem mặt mũi của hoàng thất để ở đâu.\r\n\r\nTạ Uyên tựa hồ đoán được ta đang suy nghĩ gì, lúng túng ho khan một tiếng: \"Bên ngoài gió lạnh, chúng ta vào trước đi.\"\r\n\r\nTrong điện, khói thuốc từ từ bay lên qua ánh đèn.\r\n\r\nTạ Uyên từ trong ngực móc ra một cây trâm, đeo lên cho ta: \"Ninh Ninh, đây là vật duy nhất nhạc mẫu để lại cho nàng, về sau không nên tùy tiện đưa cho người khác.”\r\n\r\nTa bây giờ chỉ có một thân một mình, không có gì để lo lắng, nói chuyện cũng không cần cố kỵ.\r\n\r\n\"Bệ hạ có mặt mũi nói ra những lời này sao, ngài thật sự không biết Cố gia trung thành toàn bộ đều chết oan? Cũng không biết vì sao ta lại mang cây trâm này đưa ra sao?”\r\n\r\nNgười hầu trong điện ào ào quỳ xuống.\r\n\r\nTạ Uyên tự cảm thấy xấu hổ, chỉ nói một câu nghỉ ngơi thật tốt rồi chạy chối chếc.\r\n\r\n\"Nương nương, hôm đó bệ hạ thấy ngài không đeo chiếc trâm này trên đầu, liền phái người đi thăm dò, những thái giám chạm vào trâm cài của ngài đều bị bệ hạ ban chế/t, địa vị của ngài trong lòng hoàng thượng là không giống.”\r\n\r\nTa liếc nhìn cung nữ đang nói.\r\n\r\n\"Ngươi tên là gì?\"\r\n\r\n\"Nô tỳ tên là Đạm Nguyệt.\"\r\n\r\nNha hoàn hồi môn của ta đều đã chế.t trong trận “Mưu phản” của cố Gia.\r\n\r\nCả gia tộc Cố gia đều bị ché.m đầu, chỉ để lại một mình ta.\r\n\r\n7.\r\n\r\nHôm qua tuyết rơi dày đặc, trong viện một màu trắng xóa, trên bàn đá có một bếp để pha trà, nắp ấm trà đang sôi, hơi nóng tỏa ra.\r\n\r\nLâm Thanh không để ý đến cung nhân thông báo rằng không hợp quy củ mà vẫn xông vào, khóc nói: \"Ta cùng những nữ nhân trong hậu cung kia không giống nhau, ta không phải vì địa vị mà yêu bệ hạ, ta thật sự yêu hắn...\"\r\n\r\nTrong miệng nàng ta lúc nào cũng tình tình yêu yêu, ta không rõ, thời đại kia của các nàng chẳng lẽ trong đầu chỉ có tình yêu thôi sao?\r\n\r\nLâm thanh than thở khóc lóc, các thái giám cung nữ hầu hạ xung quanh lập tức cúi đầu xuống.\r\n\r\nTa hơi ngạc nhiên, nàng ta đúng thật là không chút nào để ý đến tư thái, cũng không sợ hạ nhân chê cười.\r\n\r\nTa nhếch môi cười nhẹ: “Người ngươi nói yêu chính là bệ hạ, nếu như bệ hạ là một tên thái giám thì ngươi vẫn yêu sao?”\r\n\r\nLâm Thanh tắt tiếng.\r\n\r\nĐạm Nguyệt ở bên cạnh suốt ruột nháy nháy mắt với ta.\r\n\r\nTa trả lời thay nàng ta: “Ngươi sẽ không, lúc trước ngươi gặp bệ hạ, vừa thấy đã yêu vì ngươi biết thân phân của hắn, nếu như lúc đó ngươi không có nhìn thấy bệ hạ, ngày sau ngươi cũng sẽ tìm đủ mọi cách để làm cho bệ hạ chú ý, ta không biết ngươi ở thế giới kia như thế nào, nhưng ta biết ngươi tham luyến vinh hoa phú quý, ngươi muốn được mọi người tôn sùng, hưởng thụ tư vị có được quyền thế, Lâm phi, bản cung nói không sai chứ?”\r\n\r\nSắc mặt Lâm Thanh trắng bệch, cố gắng giải thích: “Ban đầu ta chỉ nghĩ muốn thử gặp hoàng đế của triều đại này, nhưng hiện tại ta thật sự yêu hắn.”\r\n\r\n“Cho nên, tình yêu xen lẫn tư tâm cũng đừng nói đến thuần túy, vĩ đại như vậy.”\r\n\r\nLâm Thanh đứng tại chỗ, há miệng muốn nói, nhưng lại không nói được gì.\r\n\r\nĐạm Nguyệt con mắt đều sắp nháy m.ù: “Tham kiến bệ hạ.”\r\n\r\nTa liếc mắt nhìn Tạ Uyên đang cầm hoa mai trong tay đi tới.\r\n\r\nBây giờ mỗi khi vào triều, hắn sẽ đích thân bẻ một chùm hoa mai từ Ngự Hoa Viên mang tới.\r\n\r\nTạ Uyên nhìn thấy Lâm Thanh ở chỗ này, rất bất mãn: \"Ngươi không phải đang bị cấm túc sao, ai cho phép ngươi ra.”\r\n\r\nLâm Thanh lau nước mắt, lại bắt đầu hồ ngôn loạn ngữ: \"Bệ hạ, trước kia ta cùng ngài nói qua người với người là bình đẳng, ngài nên dùng quyền lực trong tay để làm nhiều việc có lợi hơn cho bách tính xã tắc, chứ không nên dùng quyền lực trách phạt người khác.”\r\n\r\nĐối với loại lời nói này của nàng ta, ta đã chậm rãi quen thuộc.\r\n\r\nTạ Uyên nghe thấy điều này chỉ cảm thấy bực bội không thể giải thích được.\r\n\r\nMọi người bình đẳng?\r\n\r\nNgười hầu ở đây ai cũng câm như hến, cùng đế vương bàn luận người người bình đẳng.\r\n\r\nBuồn cười đến cực điểm!\r\n\r\nNhưng hết lần này tới lần khác Lâm Thanh không có ý thức được, còn đang líu lo không ngừng.\r\n\r\n\"Bệ hạ, hôm nay ta mới làm một bài thơ, ta đọc cho ngài nghe một chút?”\r\n\r\nNói xong, không đợi Tạ Uyên mở miệng, liền đọc.\r\n\r\nNhìn thấy những người xung quanh không hề ngạc nhiên trước hành vi không có quy củ này của Lâm Thanh, Tạ Uyên chỉ cảm thấy mất mặt, điều này chứng tỏ đều là hắn dung túng mà ra.\r\n\r\n“Sáng nhìn trời, chiều ngắm mây, đi cũng nhớ chàng, ngồi cũng nhớ chàng.”\r\n\r\nNàng thẹn thùng nhìn về phía Tạ Uyên.\r\n\r\nTạ Uyên nghe được lời này sắc mặt trở nên khó coi, cho dù nữ nhân thời xưa muốn bày tỏ tình yêu cũng sẽ lén lút nói ra, sao có thể nói ra lời này trước mặt nhiều cung nhân như vậy.\r\n\r\n\"Từ Thịnh!\"\r\n\r\n\"Có nô tài.\"\r\n\r\n\"Lâm phi điên rồi, lệnh thái y vào cung chữa trị cho nàng thật tốt, khi nào bình phục sẽ thả nàng ra.”\r\n\r\n\"Vâng.\"\r\n\r\nGiải quyết xong phiền phức, Tạ Uyên ấn ấn mi tâm, chỉ cảm thấy đầu đau như búa bổ.\r\n\r\n\"Ninh Ninh, trẫm làm sao lại rơi vào hoàn cảnh mất đi nàng như vậy?\"\r\n\r\nTa cụp mắt xuống và nhấp một ngụm trà.\r\n\r\n\"Có lẽ là ngài lần thứ nhất bị Lâm Thanh hấp dẫn, hoặc là ngài vì nàng ta mà lần đầu tiên răn dạy ta...”\r\n\r\n8.\r\n\r\nTa cùng Tạ Uyên vừa ra đời liền có hôn ước, là thanh mai trúc mã cùng nhau lớn lên.\r\n\r\nNếu có người ức hiếp ta, hắn nhất định sẽ là người đầu tiên giúp ta đánh trả, cho dù biết sau khi hồi cung sẽ bị trách phạt, nhưng hắn vẫn làm như vậy.\r\n\r\nKhông lâu sau khi hắn đang cơ ta liền lâm bệnh nặng.\r\n\r\nBệnh tình hung hiểm, ngay cả các thái y đều thúc thủ vô sách, Tạ Uyên thức trắng đêm không ngủ để chăm sóc ta, sao nhãng việc triều chính, khiến một số người trong triều đình thay nhau chỉ trích ta, lúc đó hắn liền rút kiếm lên triều, suýt chút giế.t chế.t đối phương, kể từ lúc đó, tất cả mọi người đều biết rằng ta không chỉ là hoàng hậu, mà còn là vị thê tử mà hắn đã nhận định trong lòng.\r\n\r\nThái y quỳ đầy đất, nhưng không ai có thể nghĩ ra được phương thuốc tốt để chữa trị cho ta.\r\n\r\nTạ Uyên không biết từ đâu nghe nói lối vào Hộ Quốc tự có chín mươi chín bậc thang, chỉ cần một bước một quỳ, liền có thể đạt được điều mình mong muốn, cầu bình an cho người thân.\r\n\r\nHắn quỳ từng bước một từ đêm khuya cho đến khi mặt trời mọc, long bào ở đầu gối đều bị rách, hắn mang theo bùa bình an trở về, dáng vẻ chật vật khiến người chấn kinh.\r\n\r\nSau khi ta khỏi bệnh, câu chuyện Bệ hạ bước đi bước quỳ lên chín mươi chín bậc thang đã lan truyền khắp kinh đô.\r\n\r\nKhi đó ta còn cho rằng mình là người hạnh phúc nhất trên thế gian.\r\n\r\nNhưng khoảng thời gian tốt đẹp đó chẳng kéo dài được bao lâu, sự xuất hiện của Lâm Thanh khiến ta nhận ra rằng đàn ông có thể thay lòng đổi dạ.\r\n\r\nNgày đó, cung nhân hoảng hốt chạy đến báo tin, trong giếng cạn La trai đã phong kín từ lâu không hiểu sao xuất hiện một người, bệ hạ còn phong nàng ta làm quý phi.\r\n\r\n\"Nương nương, chẳng lẽ là có yêu nghiệt mê hoặc bệ hạ.”\r\n\r\nTa đè nén chua xót trong lòng, nghiêm mặt nói: \"Nói bậy, trong cung làm sao có yêu nghiệt, bệ hạ chỉ sắc phong một phi tử mà thôi, ngươi lựa chút đồ trong khố phòng đưa cho Lâm quý phi đi.”\r\n\r\n\"Vâng.\"\r\n\r\nTheo quy củ, Hoàng hậu ban thưởng là phải tới tạ ơn.\r\n\r\nLiên tiếp mấy ngày, vị Lâm quý phi kia cũng không có động tĩnh gì.\r\n\r\nCung nữ Hoàn Nhan bất mãn: “Cái thứ gì chứ, chỉ là một nữ nhân không rõ lai lịch, ỷ vào việc được bệ hạ sủng ái, thậm chí ngay cả ngài cũng không để vào mắt, từ khi nàng ta xuất hiện, bệ hạ đã bao lâu rồi không đến Phượng Ninh cung của chúng ta.”\r\n\r\nTiền triều có đại thần bất mãn.\r\n\r\nHậu cung thì có Thái hậu gây áp lực, để cho ta khuyên nhủ bệ hạ.\r\n\r\nTrong cung thậm chí còn có lời đồn, Lâm quý phi là yêu quái, nếu những tin đồn này không được xua tan, sợ là danh tiếng của bệ hạ cũng sẽ bị liên lụy.\r\n\r\n\"Hoán nhan, kêu phòng bếp chuẩn bị một phần bánh hoa quế, chúng ta đi Dưỡng Tâm điện.”\r\n\r\nHoán Nhan cho rằng ta cuối cùng cũng ra tay, nên vui vẻ đáp: “Vâng!”\r\n\r\nTrước cửa Dương Tâm điện, ta còn chưa bước vào, đã nghe thấy bên trong truyền ra tiếng cười: \"Bệ hạ, ta đã nói rồi, ta có thể sáng tác rất nhiều thơ ca, hiện tại người tin rồi đi.\"\r\n\r\n\"Tin tin tin, ngược lại là trẫm đã xem thường ngươi.\"\r\n\r\n\"Đúng nha, ta thế nhưng là nữ nhân độc lập ở thời đại mới!\"\r\n\r\nTừ thịnh lúng túng nhìn ta một chút: \"Hoàng hậu nương nương, mời ngài.\"\r\n\r\nTa đi về phía trong điện, đúng theo khuôn phép nói: \"Thần thiếp tham kiến bệ hạ.\"\r\n\r\n\"Ninh Ninh, nàng đến rồi, mau đứng dậy đi, mấy ngày nay trẫm bề bộn công vụ, có chút lơ là nàng, nàng sẽ không trách trẫm chứ.”\r\n\r\n\"Bệ hạ lấy quốc sự làm trọng, thần thiếp đều hiểu.\"\r\n\r\nLâm Thanh đứng bên cạnh bệ hạ, không có chút ý tứ nào muốn hành lễ.\r\n\r\nHoán Nhan đè nén khinh thường, nhắc nhở nàng: \"Lâm quý phi nên hành lễ vấn an hoàng hậu nương nương.”\r\n\r\n\"Mọi người sinh ra đều bình đẳng, ai cũng không thấp kém hơn ai, tại sao ta phải quỳ.”\r\n\r\nĐây là lần đầu tiên ta được nghe Lâm Thanh nói về “Đại đạo lý”.\r\n\r\n\"Làm càn! Hoàng hậu chính là nhất quốc chi mẫu, ngươi quỳ vốn là chuyện đương nhiên.\"\r\n\r\n\"Các ngươi đây đều là tư tưởng phong kiến, vẫn nên sớm một chút bỏ đi, dù sao cũng là chuyện sớm hay muộn.\"\r\n\r\nTa cho Hoán Nhan một ánh mắt để nàng đừng nói tiếp, kết quả một giây sau liền nghe được Lâm Thanh nói lời đại nghịch bất đạo, ta giật mình ngẩng đầu nhìn về phía bệ hạ.\r\n\r\nHắn dường như đã quen với việc đó, không hề có dấu hiệu tỏ ra tức giận.\r\n\r\nNgược lại hắn cười to hai tiếng, nói: \"Ninh Ninh là người bao dung rộng lượng, tất nhiên là sẽ không cùng ngươi so đo.”\r\n\r\nSau đó hắn lại nói với ta: \"Tính cách của Thanh nhi cùng những người khác có hơi khác biệt, Ninh Ninh cứ làm quen dần là được, trẫm cũng thường xuyên bị cô ấy chọc cho không biết nên khóc hay cười.”\r\n\r\nLời nói đại nghịch bất đạo như thế bệ hạ đều có thể cười trừ, ta siết chặt khăn tay, không quên chính sự hôm nay.\r\n\r\n\"Bệ hạ, Lâm quý phi đột nhiên xuất hiện ở giếng cạn, hiện tại lời đồn đang lan truyền bốn phía, không biết Lâm quý phi vì nguyên do gì lại xuất hiện ở kia, bản cung cũng có lời giải thích cho mọi người.”\r\n\r\nLâm Thanh không dám kể hết những chuyện ở thời hiện đại.\r\n\r\n\"Bệ hạ, ta có chuyện khó nói, ở đây xác thực lại không có thân phận gì, nếu không ngài an bài giúp ta một cái nhé?\" nàng ta cười nhẹ nhàng dựa lại gần Tạ Uyên.\r\n\r\n\"Nếu ngươi họ Lâm, vậy liền tuyên bố với bên ngoài là người nhà của Lâm tướng quân, ngoài ý muốn đi lạc trong cung.”\r\n\r\nTa suýt nữa đứng không vững: “Bệ hạ, ngài đang nói đến Lâm tướng quân chiến công hiển hách sao?”\r\n\r\n\"Đúng vậy, ngươi phái người đưa thư cho Lâm phu nhân, thông báo một tiếng.\"\r\n\r\nThấy hắn đã quyết định, ta cũng không thể nói thêm gì nữa: “Vâng.”\r\n\r\n\"Ninh Ninh, nàng tới xem những bài thơ này của Thanh Nhi viết như thế nào?\"\r\n\r\nTa bước tới, cầm tờ giấy lên: “Mồng 8 tháng 9 mùa thu đến trăm hoa đua nhau nở, hương bay khắp Trường An, cả kinh thành đều ngập trong sắc vàng.”\r\n\r\nĐọc xong, ta vẫn chưa thoả mãn: “Lâm quý phi tài hoa hơn người, có tài viết văn xuất sắc”.\r\n\r\nLâm Thanh cười đùa nói: \"Đó là đương nhiên.\"\r\n\r\n9.\r\n\r\nNgày hôm sau thỉnh an Thái hậu.\r\n\r\nLâm Thanh đã được sắc phong nhiều ngày, nhưng lại chưa bao giờ đến Thọ an cung thỉnh an, Thái hậu không hài lòng ra lệnh cho Triệu ma ma đích thân đi mời.\r\n\r\nLâm quý phi đến nơi còn đang ngáp dài, lẩm bẩm tại sao phải dậy sớm như vậy, Thái hậu nghe vậy tại chỗ tức giận, sai người vả miệng hai mươi cái.\r\n\r\nLâm Thanh giãy dụa nói muốn gặp bệ hạ.\r\n\r\nThái hậu sắc mặt lạnh lùng nói: \"Ai gia muốn trừng phạt ngươi, cho dù bệ hạ tới thì lại như thế nào! Đánh!\"\r\n\r\n\"Ba, ba, ba......\"\r\n\r\nLiên tiếp hai mươi cái tát, mỗi cái đều toàn lực, khuân mặt của Lâm Thanh sưng tấy, dấu tay hiện rõ.\r\n\r\nTa liếc mắt ra hiệu cho Hoán Nhan, sai nàng đi tìm thái y.\r\n\r\n\"Trách phạt hôm nay là cảnh cáo ngươi trong cung phải hiểu được tôn ti, tuân theo quy củ, được rồi, hôm nay ai gia cũng mệt mỏi, đều lui ra đi, hoàng hậu lưu lại.”\r\n\r\nTa bị đơn độc lưu lại Thọ An cung.\r\n\r\nThái hậu nhấp một ngụm trà, chậm rãi mở miệng: “Hoàng hậu, Ai gia biết từ khi vị Lâm quý phi này xuất hiện, con chịu không ít ủy khuất, hôm nay ai gia cũng là vì con trút giận.”\r\n\r\nThái hậu không phải là mẫu thân thân sinh của bệ hạ, nhiều năm minh tranh ám đấu như vậy, ta nói lời thật lòng: “Thần thiếp tiếp nhận hoàng ân, chưa hề cảm thấy ủy khuất, lời này của thái hậu, thần thiếp sợ hãi.”\r\n\r\nThái hậu đặt chén trà xuống: “Vậy là tốt rồi, bất quá có một số việc con thân là hoàng hậu nên chủ động đi làm, như Lâm quý phi, người không rõ thân phận không thể vì hoàng thất sinh con nối dõi, con hiểu chưa?”\r\n\r\nÝ của bà là muốn ta ban thưởng canh tuyệt tử, ta đứng dậy quỳ xuống: “Hồi bẩm Thái hậu nương nương, tha thứ thần thiếp khó mà tòng mệnh.\"\r\n\r\nThái hậu cười nói: “Ai gia biết con thiện lương không xuống tay được, không bắt buộc con đi làm, chuyện này con không cách nào thay ai gia an bài thỏa đáng, vậy thì giúp ai gia xử lý chuyện khác đi, ai gia muốn cùng bệ hạ hòa hoãn mối quan hệ, chỉ là không biết ngày thường hắn thích cái gì, về sau những chuyện thường ngày của bệ hạ ngươi nhiều chút nói cho ai gia, thì thế nào?”\r\n\r\nTa cúi người trên mặt đất.\r\n\r\nThái hậu nổi giận ném chén trà vào người ta, để cho ta cút ra ngoài quỳ hai canh giờ.\r\n\r\n10.\r\n\r\nTa trở lại cung Phượng Ninh, Hoán Nhan đau lòng giúp ta chườm đá đầu gối.\r\n\r\n\"Nương nương, tại sao người lại cùng thái hậu đối nghịch, đắc tội thái hậu, trong hậu cung này chắc chắn sẽ không dễ sống.”\r\n\r\nÝ tứ của Thái hậu rõ ràng là muốn ta làm con tốt bên cạnh Bệ hạ, lúc nào cũng báo cáo, ta làm sao có thể đồng ý được?\r\n\r\nTrong lúc thất thần, bệ hạ tới.\r\n\r\n\"Tham kiến bệ hạ.\"\r\n\r\nTạ Uyên lần đầu tiên không dìu ta dậy, ta nhận ra tâm tình của hắn không tốt, còn chưa mở miệng, hắn đã mắng ta một trận: “Trẫm vốn cho rằng nàng là người khéo léo hiểu lòng người, không nghĩ tới cũng học những người kia tranh giành tình cảm, hôm nay Thanh nhi ở chỗ Thái hậu bị phạt, vì sao nàng không giúp cầu tình, tất cả mọi người đều lui ra, vì sao Thái hậu chỉ lưu lại một mình nàng!”', '', 5, '2024-04-24 23:31:52', '2024-04-24 23:31:52'),
(12, '', 'uploads/2024/06/10/nguyenton11.jpg', 7, '2024-05-10 02:13:04', '2024-06-09 23:25:30'),
(13, '', 'uploads/2024/06/10/nguyenton12.jpg', 7, '2024-05-10 02:13:04', '2024-06-09 23:25:30'),
(17, '', 'uploads/2024/05/10/nguyenton31.jpg', 8, '2024-05-10 02:13:55', '2024-05-10 02:13:55'),
(18, '', 'uploads/2024/05/10/nguyenton32.jpg', 8, '2024-05-10 02:13:55', '2024-05-10 02:13:55'),
(19, '', 'uploads/2024/05/10/nguyenton33.jpg', 8, '2024-05-10 02:13:55', '2024-05-10 02:13:55'),
(20, '', 'uploads/2024/06/09/de_tu_01.jpg', 9, '2024-06-09 02:58:57', '2024-06-09 02:58:57'),
(21, '', 'uploads/2024/06/09/toi-thang-cap-bang-cach-thuong-cho-nhung-de-tu.jpg', 9, '2024-06-09 02:58:57', '2024-06-09 02:58:57'),
(25, '', 'uploads/2024/06/10/nguyenton03.jpg', 7, '2024-06-09 23:26:30', '2024-06-09 23:26:30'),
(26, '', 'uploads/2024/06/10/nguyenton11.jpg', 7, '2024-06-09 23:26:30', '2024-06-09 23:26:30'),
(27, '', 'uploads/2024/06/10/nguyenton12.jpg', 7, '2024-06-09 23:26:30', '2024-06-09 23:26:30'),
(28, '', 'uploads/2024/06/10/nguyenton13.jpg', 7, '2024-06-09 23:26:30', '2024-06-09 23:26:30');

-- --------------------------------------------------------

--
-- Table structure for table `mangas`
--

CREATE TABLE `mangas` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `menu_id` bigint(20) UNSIGNED NOT NULL,
  `category_id` bigint(20) UNSIGNED NOT NULL,
  `active` int(11) NOT NULL COMMENT '1: hoạt đông, 0 là ko hoạt đông',
  `suggest` int(11) NOT NULL COMMENT '1:đề xuất, 0: không đề xuất',
  `number_like` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `description` text NOT NULL,
  `number_reads` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `mangas`
--

INSERT INTO `mangas` (`id`, `image`, `name`, `author`, `menu_id`, `category_id`, `active`, `suggest`, `number_like`, `created_at`, `updated_at`, `description`, `number_reads`) VALUES
(1, 'uploads/2024/06/09/ki_uc_phai_mau.jpg', 'Kí Ức Phai Màu', '白桃汽水', 2, 9, 1, 1, 3, '2024-04-19 04:45:12', '2024-06-09 02:53:02', 'Ta bị phế hậu.\r\n\r\nTừ trước đến nay trong cung luôn nâng cao đạp thấp, thái giám giọng the thé nói: “Nương nương, theo ý chỉ của hệ hạ, ngài không được phép mang theo bất cứ thứ gì, cho nên con súc sinh này ta sẽ mang đi.”\r\n\r\nChú chó nhỏ trong ngực cảm nhận được ác ý, toàn thân run rẩy.', 67),
(2, 'uploads/2024/04/19/hoi_tiet_thoi_thanh_xuan.jpg', 'Hối Tiết Thời Thanh Xuân', '菜菜', 2, 9, 1, 1, 1, '2024-04-19 04:46:20', '2024-04-19 07:56:07', 'Hối Tiếc Thời Thanh Xuân\r\nTác giả: 菜菜\r\nEdit: Mer\r\n\r\nTôi có một người bạn trai, là một thiếu gia con nhà giàu thực thụ.\r\n\r\nAnh ta cho rằng tôi là người đơn thuần, ngây ngô đến ê răng, thời điểm muốn chơi đùa thì chơi đùa, muốn vứt bỏ thì liền vứt bỏ.', 23),
(5, 'uploads/2024/04/19/cung_nu_mo_hon_bi_doc_tam.jpg', 'Cung nữ mỏ hỗn bị đọc tâm', '一把鱼骨头', 2, 2, 1, 1, 1, '2024-04-19 07:59:24', '2024-04-19 07:59:24', 'CUNG NỮ MỎ HỖN BỊ ĐỌC TÂM\r\nTác giả: 一把鱼骨头\r\n________\r\n\r\nThấy tôi càng ngày càng chán nản, xung quanh không ai có thể khuyên nhủ được.\r\n\r\nThái giám tổng quản mỗi khi nhìn thấy tôi đều thở dài, khuôn mặt nhỏ mũm mĩm tràn đầy sầu khổ.\r\n\r\nCho đến khi, quý phi tới.', 68),
(6, 'uploads/2024/04/19/mot_chin_mot_muoi.jpg', 'Một Chín Một Mười', '张若妤', 2, 2, 1, 1, 0, '2024-04-19 08:00:33', '2024-04-19 08:00:33', 'Ta đã phải lòng hoàng thượng 10 năm, hắn từng vô số lần nhục nhã, giày vò ta bằng chuyện hoan ái nam nữ.\r\n\r\nNhiều năm sau, hắn trở thành tù nhân của ta.\r\n\r\nTa trói cổ tay của hắn lại, ra lệnh cho một người đàn ông trung niên khỏe mạnh đi vào phòng giam của hắn cả một đêm...', 2),
(7, 'uploads/2024/04/19/tieu_phu_ba_menh_kho.jpg', 'Tiểu Phú Bà Mệnh Khổ', 'Vô Danh', 2, 9, 1, 1, 0, '2024-04-19 08:01:27', '2024-04-19 08:01:27', 'TIỂU PHÚ BÀ MỆNH KHỔ \r\n****???****\r\nTôi mắc một căn bệnh.\r\nKhông thể mặc quần áo có giá hơn 200 tệ và không thể ăn đồ ăn có giá hơn 30 tệ.\r\nCho nên, khi tôi đang chọn quần áo mới ở các sạp hàng trên phố và bôn ba ở khu giá đặc biệt của căn tin, sẽ nghe thấy những giọng nói như thế này:\r\nHoa khôi lớp: \"Mọi người đừng kỳ thị Giang Độ, cô ấy chỉ nghèo một chút mà thôi.\"\r\nCô giáo: \"Lãnh đạo sẽ đến, Giang Độ, hôm nay em đừng xuất hiện ở trường.\"\r\nHả? Nhưng tài sản cá nhân của tôi tận mấy tỷ, lãnh đạo đến trường học hôm nay là đàn em của tôi.', 17),
(8, 'uploads/2024/04/19/thuy_hu_truyen.jpg', 'Thuỷ Hử Truyện', 'Thi Nại Am', 2, 3, 1, 1, 1, '2024-04-19 08:02:22', '2024-04-19 08:02:22', 'THUỶ HỬ TRUYỆN\r\n--------??------------\r\nVề đời nhà Tống, Triết Tôn Hoàng Đế làm vua, cách với đời của vua Nhân Tôn Hoàng Đế đã xa; Giữa phủ Khai Phong, ở ngay Đông Kinh, thuộc khu của thành Tuyên Vũ Quân nảy nồi một tên con nhà lông bông mất dạy, là dòng họ Cao, bày hàng đứng vào thứ hai.\r\n\r\nAnh ta thuở nhỏ không chịu làm ăn, chỉ thích múa thương đánh gậy, và đá cầu rất giỏi, bởi thế trong Kinh Sư gọi anh ta là Cao Cầu, đổi gọi Cao Nhị. Đoạn chữ cầu có chữ Mao là lông ở bên cạnh, sau phát tích khá lên, mới đổi chữ Cầu có chữ Nhân là người đứng cạnh.', 1),
(9, 'uploads/2024/04/19/dongchulietquoc.webp', 'Đông Chu Liệt Quốc', 'Phùng Mộng Lông', 2, 3, 1, 1, 1, '2024-04-19 08:03:22', '2024-04-19 08:03:22', 'Đông Chu Liệt Quốc\r\n-------------------?\r\nTruyện Đông Chu Liệt Quốc là một truyện mới được giới thiệu với bạn đọc trên trang đọc truyện online, một câu chuyện ghi lại dấu ấn lịch sử, một thời vàng son trong quá khứ của những vương triều đi vào sử sách, chỉ còn là truyền thuyết cho hậu thế. Truyện lịch sử, quân sự Đông Chu Liệt Quốc ngay từ khi vừa ra mắt đã nhận được sự chú ý và ủng hộ của đông đảo bạn đọc. Vốn dĩ tưởng là những đoạn văn khô khan nhưng dưới ngòi bút của tác giả Phùng Mộng Long lại trở nên sống động, gần gũi.', 140),
(10, 'uploads/2024/04/19/giangsonchiendo.webp', 'Giang Sơn Chiến Đồ', 'Cao Nguyệt', 2, 3, 1, 1, 4, '2024-04-19 08:04:20', '2024-04-19 08:04:20', 'GIANG SƠN CHIẾN ĐỒ\r\nTác giả: Cao Nguyệt\r\n---------??----------\r\nThế gian xoay vần, thiên địa chuyển di. Mạt Tuỳ đã điểm, anh hùng chiến tử. Nhất thống giang sơn, tạo uy tế thế. Kiến công lập nghiệp, đáng mặt trượng phu.\r\n\r\nẤy chà chà, ấy chà chà... Lỡ tay giết người không phải chuyện đáng quan ngại. Nhưng giết lầm người mới là chuyện lớn trong lịch sử', 15),
(11, 'uploads/2024/04/19/vuot-qua-thoi-khong-yeu-chang.jpg', 'Vượt Qua Thời Không Yêu Chàng', 'Thanh Phong Nhất Tiêu', 2, 4, 1, 1, 0, '2024-04-19 08:05:48', '2024-04-19 08:05:48', 'VƯỢT QUA THỜI KHÔNG ĐỂ YÊU CHÀNG\r\nTác giả: Thanh Phong Nhất Tiếu\r\n******************************\r\nTrần Thanh Thanh bất ngờ xuyên đến một triều đại không có trong lịch sử. Thế nhưng người ta xuyên đến được làm nữ chính, nữ phụ, nữ xyz,... mà đến lượt nàng xuyên không thì đến cả một cái thân thể cũng không có, phải làm cô hồn dã quỷ lang thang trong hoàng cung.\r\n\r\n\r\nVà cũng từ đây, nàng quen biết được với Tứ Hoàng tử Hàn Kỳ.\r\n\r\n\r\nMọi chuyện sẽ tiếp diễn ra sao, mọi người cùng đón xem nhé!', 306),
(16, 'uploads/2024/05/10/nguyen-ton.jpg', 'Nguyên Tôn', 'Thiên Tằm Thổ Đậu', 1, 11, 1, 1, 1, '2024-05-10 02:12:26', '2024-05-10 02:12:26', 'Thể Loại: Action Adventure Fantasy Manhua Mystery Truyện Màu.\r\nMình rất vui vì được gặp lại các bạn trong tập ngày hôm nay. Ở tập trước chúng ta đã cùng nhau tìm hiểu khá nhiều về \"Validation Laravel\" rồi, nhưng hôm nay chúng', 3),
(17, 'uploads/2024/06/09/toi-thang-cap-bang-cach-thuong-cho-nhung-de-tu.jpg', 'TÔI THĂNG CẤP BẰNG CÁCH THƯỞNG CHO NHỮNG ĐỆ TỬ', 'Đang Cập Nhật', 1, 11, 1, 0, 0, '2024-06-09 02:56:42', '2024-06-09 02:56:42', 'Như các bạn đã biết, NetTruyen là website đọc truyện tranh với số lượng user gần như lớn nhất tại Việt Nam, chúng tôi luôn cố gắng cập nhật và tìm kiếm những bộ truyện mới nhất với đầy đủ thể loại như: Truyện tranh ngôn tình, truyện tranh xuyên không, truyện manhua, đam mỹ, cổ đại... Đọc truyện tranh online tại website NetTruyen sẽ giúp chúng tôi có kinh phí duy trì và phát triển để xây dựng cộng đồng lớn mạnh hơn.\r\nSư phó giá hạc tây thiên, kẻ thù tìm tới môn phái làm sao bây giờ?! Thức tỉnh thụ đồ phản lợi hệ thống, nhận đồ đệ càng nhiều, lợi ích sư phó càng nhiều! Ha ha ha, các đồ nhi ngoan, mau tới đây, vi sư chưa bao giờ tàng tư!', 14),
(25, 'uploads/2024/06/11/doraemon.jpg', 'Doremon', 'Fujiko Fujio', 1, 17, 1, 0, 0, '2024-06-11 00:29:53', '2024-06-11 00:29:53', 'Doraemon (ドラえもん (銅鑼右衛門[2]) (Đồng La Hữu Vệ Môn)/ どらえもん/ ドラエモン? tên cũ tại Việt Nam là Đôrêmon) là nhân vật chính hư cấu trong loạt Manga cùng tên của họa sĩ Fujiko F. Fujio.', 3);

-- --------------------------------------------------------

--
-- Table structure for table `manga_likes`
--

CREATE TABLE `manga_likes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `manga_id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `like` int(11) NOT NULL COMMENT '1:like',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `manga_likes`
--

INSERT INTO `manga_likes` (`id`, `manga_id`, `user_id`, `like`, `created_at`, `updated_at`) VALUES
(10, 1, 1, 1, NULL, NULL),
(12, 2, 2, 1, NULL, NULL),
(14, 8, 2, 1, NULL, NULL),
(18, 5, 3, 1, NULL, NULL),
(19, 9, 2, 1, NULL, NULL),
(20, 1, 2, 1, NULL, NULL),
(28, 1, 3, 1, NULL, NULL),
(30, 16, 2, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `menus`
--

CREATE TABLE `menus` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `menus`
--

INSERT INTO `menus` (`id`, `name`, `image`, `created_at`, `updated_at`, `active`) VALUES
(1, 'Truyện tranh', 'uploads/2024/04/21/pngpen.jpg', '2024-04-21 01:50:20', '2024-04-21 01:50:20', 1),
(2, 'Tiểu thuyết', 'uploads/2024/04/21/tieuthuyet.png', '2024-04-21 01:50:38', '2024-04-21 01:50:38', 1),
(3, 'Phân loại', 'uploads/2024/04/21/phan_loai.webp', '2024-04-21 01:50:49', '2024-04-21 01:50:49', 1),
(4, 'Xếp hạng', 'uploads/2024/04/25/xep_hang.png', '2024-04-21 01:51:09', '2024-04-25 02:29:12', 1);

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_reset_tokens_table', 1),
(3, '2019_08_19_000000_create_failed_jobs_table', 1),
(4, '2019_12_14_000001_create_personal_access_tokens_table', 1),
(5, '2024_04_17_182019_create_categories_table', 2),
(6, '2024_04_17_182453_create_categories_table', 3),
(7, '2024_04_17_185147_create_categories_table', 4),
(8, '2024_04_18_141702_create_mangas_table', 5),
(9, '2024_04_19_011929_create_menus_table', 6),
(13, '2024_04_20_082154_create_links_table', 7),
(14, '2024_04_22_094049_create_chapters_table', 8),
(15, '2024_04_22_094804_create_comments_table', 9),
(16, '2024_04_22_095000_create_manga_likes_table', 10),
(17, '2024_06_11_071420_update_table_manga', 11);

-- --------------------------------------------------------

--
-- Table structure for table `password_reset_tokens`
--

CREATE TABLE `password_reset_tokens` (
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `personal_access_tokens`
--

CREATE TABLE `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tokenable_type` varchar(255) NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `token` varchar(64) NOT NULL,
  `abilities` text DEFAULT NULL,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `expires_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `status` int(11) NOT NULL COMMENT '0: Admin, 1: user',
  `phone` int(11) NOT NULL,
  `avatar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `email_verified_at`, `password`, `remember_token`, `created_at`, `updated_at`, `status`, `phone`, `avatar`) VALUES
(1, 'Admin App', 'Admin@gmail.com', '2024-04-01 07:12:12', '$2y$12$eBAy0qKAwzoYqaAeiGU.sesOLcuiZdisH4G/WVXl.rIpUzoEzeMni', 'OIGArEb31XC9vD25pBonnfcT3B5JtVsfrCL2ML84ljVaWqiUy4b3Sn1ElPgc', '2024-04-01 07:12:12', '2024-04-01 07:12:12', 0, 0, ''),
(2, 'KhuyenKhuyen', 'daothikhuyen30@gmail.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 1, 0, ' '),
(3, 'Hello', 'khuyendt.22itb@vku.udn.vn', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 1, 0, ' file:///storage/emulated/0/Android/data/com.example.do_an_cs3/files/DCIM/IMG_20240617_003221882.jpg'),
(4, 'Mydt.2', 'Mydt.22itb@vku.udn.vn', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 1, 0, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `menu_id` (`menu_id`);

--
-- Indexes for table `chapters`
--
ALTER TABLE `chapters`
  ADD PRIMARY KEY (`id`),
  ADD KEY `manga_id` (`manga_id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comments_manga_id_foreign` (`manga_id`);

--
-- Indexes for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Indexes for table `links`
--
ALTER TABLE `links`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chapter_id` (`chapter_id`);

--
-- Indexes for table `mangas`
--
ALTER TABLE `mangas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `menu_id` (`menu_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `manga_likes`
--
ALTER TABLE `manga_likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `manga_likes_manga_id_foreign` (`manga_id`),
  ADD KEY `manga_likes_user_id_foreign` (`user_id`);

--
-- Indexes for table `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  ADD KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `chapters`
--
ALTER TABLE `chapters`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `links`
--
ALTER TABLE `links`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `mangas`
--
ALTER TABLE `mangas`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `manga_likes`
--
ALTER TABLE `manga_likes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `menus`
--
ALTER TABLE `menus`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `categories`
--
ALTER TABLE `categories`
  ADD CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `chapters`
--
ALTER TABLE `chapters`
  ADD CONSTRAINT `chapters_ibfk_1` FOREIGN KEY (`manga_id`) REFERENCES `mangas` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_manga_id_foreign` FOREIGN KEY (`manga_id`) REFERENCES `mangas` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `links`
--
ALTER TABLE `links`
  ADD CONSTRAINT `links_ibfk_1` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `mangas`
--
ALTER TABLE `mangas`
  ADD CONSTRAINT `mangas_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `mangas_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `manga_likes`
--
ALTER TABLE `manga_likes`
  ADD CONSTRAINT `manga_likes_manga_id_foreign` FOREIGN KEY (`manga_id`) REFERENCES `mangas` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `manga_likes_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
