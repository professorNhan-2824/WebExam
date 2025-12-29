-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th8 14, 2025 lúc 09:30 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quizz_web`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `exam`
--

CREATE TABLE `exam` (
  `id_exam` bigint(20) NOT NULL,
  `name_exam` varchar(255) NOT NULL,
  `date_exam` datetime(6) NOT NULL,
  `test` varchar(10) NOT NULL,
  `id_user` varchar(36) DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `exam`
--

INSERT INTO `exam` (`id_exam`, `name_exam`, `date_exam`, `test`, `id_user`, `active`) VALUES
(85, 'Phap Luat Dai Cuong', '2024-09-20 21:06:20.000000', 'ExamModal1', '736166de-9de9-40c7-98c1-5bd4b80388e6', 1),
(86, 'Toán Lớp 12', '2024-09-21 19:02:42.000000', 'ExamModal1', '736166de-9de9-40c7-98c1-5bd4b80388e6', 1),
(88, 'toan lop 1', '2024-11-05 16:01:31.000000', 'ExamModal1', 'f2369c35-f0a6-483b-9bba-479e32b242f8', 1),
(89, 'tiếng việt lớp 1', '2024-11-05 18:24:06.000000', 'ExamModal1', 'f2369c35-f0a6-483b-9bba-479e32b242f8', 1),
(92, 'toan lop 2', '2024-11-22 21:53:55.000000', 'ExamModal1', 'f2369c35-f0a6-483b-9bba-479e32b242f8', 1),
(11116, 'hoá lớp 10', '2024-12-17 17:26:23.000000', 'ExamModal1', 'f2369c35-f0a6-483b-9bba-479e32b242f8', 1),
(11117, 'thi giữa kì hoá lớp 12A3', '2025-08-12 13:04:20.000000', 'ExamModal1', '9dfbf244-579b-4f24-a2c5-8ed53bd17b16', 1),
(11119, 'quan dep trai', '2025-08-14 23:26:35.000000', 'ExamModal1', '9dfbf244-579b-4f24-a2c5-8ed53bd17b16', 1),
(11121, 'aaa', '2025-08-15 02:22:24.000000', 'ExamModal1', 'f2369c35-f0a6-483b-9bba-479e32b242f8', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `make_exam`
--

CREATE TABLE `make_exam` (
  `id` bigint(20) NOT NULL,
  `id_user` varchar(36) NOT NULL,
  `id_exam` bigint(20) NOT NULL,
  `ketqua` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `id_name` varchar(255) DEFAULT NULL,
  `exam_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `make_exam`
--

INSERT INTO `make_exam` (`id`, `id_user`, `id_exam`, `ketqua`, `created_at`, `id_name`, `exam_name`) VALUES
(15, 'f2369c35-f0a6-483b-9bba-479e32b242f8', 85, '33', '2024-11-22 18:25:40', 'quan', 'Phap Luat Dai Cuong'),
(29, 'f2369c35-f0a6-483b-9bba-479e32b242f8', 86, '33', '2024-12-17 09:21:16', 'quan', 'Toán Lớp 12'),
(30, 'f2369c35-f0a6-483b-9bba-479e32b242f8', 88, '50', '2024-12-17 10:11:20', 'quan', 'toan lop 1'),
(32, '736166de-9de9-40c7-98c1-5bd4b80388e6', 11116, '67', '2024-12-20 05:52:55', 'nhan', NULL),
(33, '9dfbf244-579b-4f24-a2c5-8ed53bd17b16', 86, '33', '2025-08-12 06:09:01', 'Nguyen dinh quan', 'Toán Lớp 12'),
(34, '9dfbf244-579b-4f24-a2c5-8ed53bd17b16', 11117, '0', '2025-08-12 06:55:43', 'Nguyen dinh quan', 'thi giữa kì hoá lớp 12A3'),
(35, '9dfbf244-579b-4f24-a2c5-8ed53bd17b16', 88, '0', '2025-08-12 07:00:20', 'Nguyen dinh quan', 'toan lop 1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `question`
--

CREATE TABLE `question` (
  `id_question` bigint(20) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer1` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `answer3` varchar(255) DEFAULT NULL,
  `answer4` varchar(255) DEFAULT NULL,
  `correct_answer` varchar(255) DEFAULT NULL,
  `id_exam` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `question`
--

INSERT INTO `question` (`id_question`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correct_answer`, `id_exam`) VALUES
(18, '1+1', '1', '2', '3', '4', '2', 85),
(19, 'Ai đẹp trai nhất', 'Nhaân', '12', '123', '234', 'Nhaân', 85),
(20, 'You can speak english or spans', 'english', 'span', 'ruisia', 'VietNam', 'VietNam', 85),
(21, '1+1', '2', '3', '4', '5', '2', 86),
(22, '2+3', '1', '2', '5', '4', '5', 86),
(23, '20+1', '2', '31', '2', '21', '21', 86),
(25, '1+1 =?', '1', '2', '3', '4', '2', 88),
(26, '2+2=?', '1', '2', '3', '4', '4', 88),
(27, 'quan đẹp', 'dai', 'trai', 'tuyệt trần', 'bình thường', 'tuyệt trần', 89),
(28, '2-2=?', '1', '2', '3', '0', '0', 92),
(29, '123-1=?', '1', '2', '3', '122', '122', 92),
(34, '3+3=?', '1', '22', '3', '6', '6', 88),
(38, '4+4=?', '1', '2', '8', '7', '8', 88),
(45, 'h2o là gì?', 'ho ho', 'hihi hoho', 'nước', 'tất cả ý trên điều đúng', 'nước', 11116),
(46, 'con người cần gì để hô hấp', 'co2', 'oxi', 'nh3', 'nitor', 'oxi', 11116),
(47, 'ban ngày cây thải ra kí gì', 'co2', 'nh3', 'oxi', 'tất cả ý trên', 'oxi', 11116),
(48, '1+1 =?', '1', '2', '3', '4', '2', 11117),
(49, '2+2=?', '1', '2', '3', '4', '4', 11117),
(50, 'nước tên hoá học là gì', 'hi', 'ha ', 'h2o', 'quan', 'h20', 11117),
(51, 'bạn Quan có đẹp trai không ', 'có', 'yes', 'đẹp trai', 'yer sir chắc chắn là như vậy rồi ', 'yer sir chắc chắn là như vậy rồi ', 11117),
(52, 'bạn muốn được bao nhiêu điểm', '10', '8', '6', '4', '4', 11117),
(54, 'quan', 'q', 'u', 'a', 'n', 'q', 11119),
(55, 'a', 'aa', 'aaa', 'aaa', 'aaaa', 'aaaa', 11121);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `spring_session`
--

CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `spring_session`
--

INSERT INTO `spring_session` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
('ba1e93c0-38f9-4d0f-b5ed-a921d71aca75', 'e066efb0-8991-4176-b4ac-48fc60c6708c', 1755199567112, 1755199593772, 1800, 1755201393772, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `spring_session_attributes`
--

INSERT INTO `spring_session_attributes` (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`, `ATTRIBUTE_BYTES`) VALUES
('ba1e93c0-38f9-4d0f-b5ed-a921d71aca75', 'user', 0xaced000573720020636f6d2e7765627175697a7a2e7765627175697a7a2e6d6f64656c2e7573657200000000000000010200074c0005656d61696c7400124c6a6176612f6c616e672f537472696e673b4c00046578616d7400104c6a6176612f7574696c2f4c6973743b4c0002696471007e00014c00086d61746b6861753171007e00014c0004726f6c6571007e00014c000b736f6469656e74686f616971007e00014c00087461696b686f616e71007e000178707400167175616e64696e683330313140676d61696c2e636f6d7372002a6f72672e68696265726e6174652e636f6c6c656374696f6e2e7370692e50657273697374656e74426167963c61233a7241920200024c000362616771007e00024c001270726f7669646564436f6c6c656374696f6e7400164c6a6176612f7574696c2f436f6c6c656374696f6e3b787200396f72672e68696265726e6174652e636f6c6c656374696f6e2e7370692e416273747261637450657273697374656e74436f6c6c656374696f6e33a4b04a3cf0460c02000b5a001b616c6c6f774c6f61644f7574736964655472616e73616374696f6e49000a63616368656453697a655a000564697274795a000e656c656d656e7452656d6f7665645a000b696e697469616c697a65645a000d697354656d7053657373696f6e4c00036b65797400124c6a6176612f6c616e672f4f626a6563743b4c00056f776e657271007e00084c0004726f6c6571007e00014c001273657373696f6e466163746f72795575696471007e00014c000e73746f726564536e617073686f747400164c6a6176612f696f2f53657269616c697a61626c653b787000ffffffff0000000074002466323336396333352d663061362d343833622d396262612d34373965333262323432663871007e0003740025636f6d2e7765627175697a7a2e7765627175697a7a2e6d6f64656c2e757365722e6578616d7070707071007e000b74003c24326124313024716356676d316d776d3572334744576c494246694465746f56497a327866565a6e597a77347a6e623543586968643633345756555774000561646d696e74000a303334363133353736317400047175616e);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `matkhau1` varchar(255) NOT NULL,
  `sodienthoai` varchar(255) NOT NULL,
  `taikhoan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `role`, `matkhau1`, `sodienthoai`, `taikhoan`) VALUES
('736166de-9de9-40c7-98c1-5bd4b80388e6', 'nhandh.22itb@vku.udn.vn', 'admin', '$2a$10$Kx.EqLcSyDPevOwqwPsRX.BW3YY2ZkKwaXVdDZFWLUB8vUrph26Iy', '1234567892', 'nhan'),
('8f6d56ad-19a3-42d9-8d04-5026fe272b51', 'dohuunhan321@gmail.com', 'user', '$2a$10$t4pH/UCQjyRs6RIelj8eve.Fpwa7WwVjF2QdSZyvc31ha4iVMRaqS', '1234567892', 'nhi'),
('9dfbf244-579b-4f24-a2c5-8ed53bd17b16', 'quandinh3011@gmail.com', 'user', '$2a$10$s8q4Oe9ApV5swE8buIBnpuEYIW54UywO9wd3e11.8I52MQ1qlEUau', '0346135761', 'Nguyen dinh quan'),
('d9a3afbf-b0b0-4980-91a7-44d8d9a4bdec', '111', 'user', '$2a$10$/Arxl2mwePCUK0sl/ESxNOp9vZqgSZpKYdg6UgsrBorJvs.EMSOCG', '111', 'dinh1'),
('f2369c35-f0a6-483b-9bba-479e32b242f8', 'quandinh3011@gmail.com', 'admin', '$2a$10$qcVgm1mwm5r3GDWlIBFiDetoVIz2xfVZnYzw4znb5CXihd634WVUW', '0346135761', 'quan');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id_exam`),
  ADD KEY `fk_user_exam` (`id_user`);

--
-- Chỉ mục cho bảng `make_exam`
--
ALTER TABLE `make_exam`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id_question`),
  ADD KEY `fk_exam_question` (`id_exam`);

--
-- Chỉ mục cho bảng `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Chỉ mục cho bảng `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  ADD KEY `SPRING_SESSION_ATTRIBUTES_IX1` (`SESSION_PRIMARY_ID`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `exam`
--
ALTER TABLE `exam`
  MODIFY `id_exam` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11122;

--
-- AUTO_INCREMENT cho bảng `make_exam`
--
ALTER TABLE `make_exam`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `question`
--
ALTER TABLE `question`
  MODIFY `id_question` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `fk_user_exam` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `fk_exam_question` FOREIGN KEY (`id_exam`) REFERENCES `exam` (`id_exam`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
