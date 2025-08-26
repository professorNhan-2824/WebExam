package com.webquizz.webquizz.Controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webquizz.webquizz.Reponsitory.MakeExamRepository;
import com.webquizz.webquizz.Service.MakeExamService;
import com.webquizz.webquizz.Service.examServiceIPM;
import com.webquizz.webquizz.Service.questionServiceIPM;

import com.webquizz.webquizz.Service.userService;
import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.make_exam;
import com.webquizz.webquizz.model.question;
import com.webquizz.webquizz.model.user;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@Controller
public class Controler {
    @Autowired
    private userService userService;
    @Autowired
    private examServiceIPM examService;
    @Autowired
    private questionServiceIPM questionServiceIPM;

    @Autowired
    private MakeExamService MakeExamService;
    @Autowired
    private MakeExamService makeExamService;
    @Autowired
    private com.webquizz.webquizz.Reponsitory.MakeExamRepository MakeExamRepository;


    @GetMapping("/")
    public String index(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        user user = (user) session.getAttribute("user");
        if (user == null) {
//            return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
            model.addAttribute("loggedIn", false);
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/signup")
    public String dangky() {
        return "dangky";
    }

    @GetMapping("/login")
    public String dangnhap() {
        return "dangnhap";
    }


    @GetMapping("/example_1")
    public String example1(HttpSession session,
                           Model model,
                           @RequestParam("IDexam") Integer idExam,
                           @RequestParam("NameExam") String nameExam) {
        user user = (user) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("IDuser", user.getId());
        model.addAttribute("IDexam", idExam);
        model.addAttribute("NameExam", nameExam);

        // Kiểm tra có câu hỏi nào cho ID exam này không
        List<question> questions = questionServiceIPM.getAllByExamId(idExam);

        if (questions != null && !questions.isEmpty()) {
            model.addAttribute("questions", questions);
        } else {
            model.addAttribute("questions", Collections.emptyList());
        }
        return "Example_1";
    }

    @GetMapping("/example_2")
    public String example_2(HttpSession session, Model model){
        user user = (user) session.getAttribute("user");
        model.addAttribute("user", user);
        return "Example_2";
    }

    @GetMapping("/createtest")
    public String createtest(HttpSession session, Model model) {
        user user = (user) session.getAttribute("user");
        if (user == null) {
            // Chuyển hướng về trang đăng nhập nếu không có session
            model.addAttribute("loggedIn", false);
            return "redirect:/login";
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
//        sessionEmail(user, model);
        model.addAttribute("user", user);
        return "createExample";
    }
//    @GetMapping("/library")
//    public String service(HttpSession session, Model model) {
//        user user = (user) session.getAttribute("user");
//        if (user == null) {
//            model.addAttribute("loggedIn", false);
//            return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
//        }else{
//            model.addAttribute("username", user.getTaikhoan());
//            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
//        }
//        List<exam> examuser = examService.getExamsByUserId(user.getId());
//            if (examuser != null && !examuser.isEmpty()) {
//            model.addAttribute("examuser", examuser);
//        } else {
//            model.addAttribute("examuser", Collections.emptyList());
//        }
//        sessionEmail(user, model);
//        model.addAttribute("user", user);
//        return "Library";
//    }

@GetMapping("/library")
public String library(@RequestParam(value = "query", required = false) String query,
                      HttpSession session, Model model) {
    // Kiểm tra session người dùng
    user user = (user) session.getAttribute("user");
    if (user == null) {
        model.addAttribute("loggedIn", false);
        return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
    } else {
        model.addAttribute("username", user.getTaikhoan());
        model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
    }

    // Tìm kiếm bài kiểm tra nếu có query, nếu không lấy tất cả bài kiểm tra
    List<exam> examuser;
    if (query != null && !query.trim().isEmpty()) {
        // Tìm kiếm theo ID hoặc tên
        examuser = examService.searchExamsByUserAndQuery(user.getId(), query);
    } else {
        // Lấy tất cả bài kiểm tra
        examuser = examService.getExamsByUserId(user.getId());
    }

    // Kiểm tra và thêm vào model
    if (examuser != null && !examuser.isEmpty()) {
        model.addAttribute("examuser", examuser);
    } else {
        model.addAttribute("examuser", Collections.emptyList());
    }

    // Thêm thông tin người dùng vào session
    sessionEmail(user, model);
    model.addAttribute("user", user); // Thêm đối tượng user vào model

    return "Library"; // Trả về view Library.html
}
// cái này để chuyển trạn thái hộng
    @PostMapping("/update-exam-status")
    public ResponseEntity<Map<String, Object>> updateExamStatus(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        try {
            String examId = payload.get("examId").toString();
            Boolean active = Boolean.parseBoolean(payload.get("active").toString());

            examService.updateExamStatus(Integer.parseInt(examId), active);

            response.put("success", true);
            response.put("message", "Cập nhật trạng thái bài thi thành công");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật trạng thái: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


//    @GetMapping("/kiemtra")
//public String kiemTra(@RequestParam(value = "query", required = false) String query,
//                      HttpSession session, Model model) {
//    // Kiểm tra session người dùng
//    user user = (user) session.getAttribute("user");
//    if (user == null) {
//        model.addAttribute("loggedIn", false);
//        return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
//    } else {
//        model.addAttribute("username", user.getTaikhoan());
//        model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
//    }
//
//    // Lấy danh sách bài kiểm tra
//    List<exam> exams;
//    if (query != null && !query.trim().isEmpty()) {
//        // Nếu có truy vấn tìm kiếm, tìm kiếm theo ID hoặc tên
//        exams = questionServiceIPM.searchExams(query);  // Giả sử bạn đã cài đặt phương thức searchExams trong service
//    } else {
//        // Nếu không có tìm kiếm, lấy tất cả bài kiểm tra
//        exams = questionServiceIPM.getAllExams();
//    }
//
//    // Kiểm tra và thêm vào model
//    if (exams != null && !exams.isEmpty()) {
//        model.addAttribute("exams", exams);
//    } else {
//        model.addAttribute("exams", Collections.emptyList());
//    }
//
//    // Thêm thông tin người dùng vào session
//    sessionEmail(user, model);
//    model.addAttribute("user", user); // Thêm đối tượng user vào model
//
//    return "kiemtra"; // Trả về view kiemtra.html
//}
@GetMapping("/kiemtra")
public String kiemTra(@RequestParam(value = "query", required = false) String query,
                      HttpSession session, Model model) {
    // Kiểm tra session người dùng
    user user = (user) session.getAttribute("user");
    if (user == null) {
        model.addAttribute("loggedIn", false);
        return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
    } else {
        model.addAttribute("username", user.getTaikhoan());
        model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
    }

    // Lấy danh sách bài kiểm tra
    List<exam> exams;
    if (query != null && !query.trim().isEmpty()) {
        exams = questionServiceIPM.searchExams(query); // Tìm kiếm theo query
    } else {
        exams = questionServiceIPM.getAllExams(); // Lấy tất cả bài kiểm tra
    }

    // Lọc danh sách bài kiểm tra chỉ lấy các bài có active = true
    List<exam> activeExams = exams != null
            ? exams.stream()
            .filter(exam -> exam.getActive() != null && exam.getActive())
            .collect(Collectors.toList())
            : Collections.emptyList();

    // Thêm danh sách đã lọc vào model
    model.addAttribute("exams", activeExams);

    // Thêm thông tin người dùng vào session
    sessionEmail(user, model);
    model.addAttribute("user", user); // Thêm đối tượng user vào model

    return "kiemtra"; // Trả về view kiemtra.html
}
    @GetMapping("/questions/{idExam}")
    public String getQuestionsByExamId(@PathVariable Integer idExam,@RequestParam String nameExam, HttpSession session, Model model) {
        // Lấy thông tin người dùng từ session
        user user = (user) session.getAttribute("user");

        if (user == null) {
            model.addAttribute("loggedIn", false);
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu người dùng chưa đăng nhập
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }

        // Lấy danh sách câu hỏi theo idExam
        List<question> questions = questionServiceIPM.getAllByExamId(idExam);
        model.addAttribute("questions", questions); // Thêm danh sách câu hỏi vào model

        // Truyền idExam và idUser vào model để sử dụng trong Thymeleaf
        model.addAttribute("idExam", idExam);
        model.addAttribute("nameExam", nameExam);
        model.addAttribute("idUser", user.getId());

        //return "online"; // Trả về trang questions.html
        return "questions";
    }
//
@GetMapping("/online/{idExam}")
public String getQuestionsOnlineByExamId(@PathVariable Integer idExam,
                                         @RequestParam String nameExam,
                                         HttpSession session,
                                         Model model) {
    // Lấy thông tin người dùng từ session
    user user = (user) session.getAttribute("user");

    if (user == null) {
        model.addAttribute("loggedIn", false);
        return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu người dùng chưa đăng nhập
    } else {
        model.addAttribute("username", user.getTaikhoan());
        model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
    }

    // Lấy danh sách câu hỏi theo idExam
    List<question> questions = questionServiceIPM.getAllByExamId(idExam);
    model.addAttribute("questions", questions); // Thêm danh sách câu hỏi vào model

    // Truyền idExam, nameExam và idUser vào model để sử dụng trong Thymeleaf
    model.addAttribute("idExam", idExam);
    model.addAttribute("nameExam", nameExam); // Thêm nameExam vào model
    model.addAttribute("idUser", user.getId());

    return "online"; // Trả về trang questions.html
}
    @GetMapping("/submit-exam")
    public String submitExam(
            @RequestParam("userId") String userId,
            @RequestParam("examId") String examId,
            @RequestParam("userName") String userName,
            @RequestParam("examName") String examName,
            @RequestParam("answers") String answersJson,
            @RequestParam("timeout") Boolean timeout,
            HttpSession session,
            Model model) {
        try {
            // Verify session
            user user = (user) session.getAttribute("user");
            if (user == null || !user.getId().toString().equals(userId)) {
                return "redirect:/login";
            }

            // Parse answers JSON
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> answers = objectMapper.readValue(answersJson, List.class);

            // Calculate score
            int correctCount = 0;
            List<Map<String, Object>> results = new ArrayList<>();
            for (Map<String, Object> answer : answers) {
                Integer questionId = Integer.parseInt(answer.get("questionId").toString());
                String selectedAnswer = (String) answer.get("selectedAnswer");
                String correctAnswer = (String) answer.get("correctAnswer");

                boolean isCorrect = selectedAnswer != null && selectedAnswer.equals(correctAnswer);
                if (isCorrect) {
                    correctCount++;
                }

                // Prepare result for each question
                Map<String, Object> result = new HashMap<>();
                result.put("questionId", questionId);
                result.put("selectedAnswer", selectedAnswer != null ? selectedAnswer : "Chưa chọn");
                result.put("correctAnswer", correctAnswer);
                result.put("isCorrect", isCorrect);
                results.add(result);
            }

            int totalQuestions = answers.size();
            String score = totalQuestions > 0 ? String.valueOf(Math.round((correctCount / (float) totalQuestions) * 100)) : "0";

            // Check if exam result exists
            List<make_exam> existingMakeExams = MakeExamRepository.findByUserAndExam(userId, examId);
            make_exam makeExam;
            if (!existingMakeExams.isEmpty()) {
                // Update existing exam result
                makeExam = existingMakeExams.get(0);
                makeExam.setKetqua(score);
                makeExam.setExamName(examName); // Cập nhật examName
            } else {
                // Create new exam result
                makeExam = new make_exam();
                makeExam.setUser(userId);
                makeExam.setExam(examId);
                makeExam.setUserName(userName);
                makeExam.setExamName(examName); // Gán examName
                makeExam.setKetqua(score);
            }
            makeExamService.save(makeExam);

            // Add data to model for done.html
            model.addAttribute("userId", userId);
            model.addAttribute("examId", examId);
            model.addAttribute("userName", userName);
            model.addAttribute("examName", examName);
            model.addAttribute("correctCount", correctCount);
            model.addAttribute("totalQuestions", totalQuestions);
            model.addAttribute("score", score);
            model.addAttribute("results", results);
            model.addAttribute("timeout", timeout);
            model.addAttribute("loggedIn", true);
            model.addAttribute("username", user.getTaikhoan());

            return "done";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Có lỗi xảy ra khi chấm điểm. Vui lòng thử lại.");
            return "error";
        }
    }
//    @GetMapping("/my-questions")
//    public String myQuestions(HttpSession session, Model model) {
//        // Lấy thông tin người dùng từ session
//        user user = (user) session.getAttribute("user");
//
//        if (user == null) {
//            model.addAttribute("loggedIn", false);
//            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu người dùng chưa đăng nhập
//        } else {
//            model.addAttribute("username", user.getTaikhoan());
//            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
//        }
//
//        // Lấy danh sách bài kiểm tra mà người dùng đã thực hiện
//        List<make_exam> userExams = MakeExamService.getExamsByUserId(user.getId());
//
//        // Thêm danh sách bài kiểm tra vào model
//        if (userExams != null && !userExams.isEmpty()) {
//            model.addAttribute("userExams", userExams);
//        } else {
//            model.addAttribute("userExams", Collections.emptyList());
//        }
//
//        // Thêm thông tin người dùng vào model
//        sessionEmail(user, model);
//        model.addAttribute("user", user);
//
//        return "my-questions"; // Trả về trang my-questions.html
//    }
@GetMapping("/my-questions")
public String myQuestions(HttpSession session, Model model) {
    user user = (user) session.getAttribute("user");

    if (user == null) {
        model.addAttribute("loggedIn", false);
        return "redirect:/login";
    } else {
        model.addAttribute("username", user.getTaikhoan());
        model.addAttribute("loggedIn", true);
    }

    List<make_exam> userExams = MakeExamService.getExamsByUserId(user.getId());
    model.addAttribute("userExams", userExams != null ? userExams : Collections.emptyList());

    sessionEmail(user, model);
    model.addAttribute("user", user);

    return "my-questions";
}
    @GetMapping("/make_exam/{idExam}")
    public String getMakeExamById(@PathVariable String idExam, HttpSession session, Model model) {
        // Kiểm tra người dùng từ session
        user user = (user) session.getAttribute("user");

        if (user == null) {
            model.addAttribute("loggedIn", false);
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Xác nhận người dùng đã đăng nhập
        }

        // Lấy danh sách từ service (giả sử lấy make_exam theo idExam)
        List<make_exam> exams = MakeExamService.getAllByExamId(idExam);
        model.addAttribute("exams", exams); // Thêm danh sách exam vào model

        // Thêm idExam và idUser để sử dụng trên giao diện
        model.addAttribute("idExam", idExam);
        model.addAttribute("idUser", user.getId());

        return "make_exam"; // Trả về trang make_exam.html
    }


    @GetMapping("/admin-exam")
    public String adminExam(HttpSession session, Model model) {
        // Kiểm tra session người dùng
        user currentUser = (user) session.getAttribute("user");

        // Kiểm tra người dùng có đăng nhập không
        if (currentUser == null) {
            model.addAttribute("loggedIn", false);
            return "redirect:/login";  // Chuyển hướng đến trang đăng nhập nếu không có session
        }

        // Kiểm tra role của người dùng
        if (!"admin".equals(currentUser.getRole())) {  // Giả sử thuộc tính 'role' lưu vai trò người dùng
            return "redirect:/";  // Chuyển hướng về trang chính nếu không phải admin
        }

        // Thêm thông tin vào model
        model.addAttribute("loggedIn", true);
        model.addAttribute("username", currentUser.getTaikhoan());

        // Lấy tất cả bài kiểm tra
        List<exam> examss = questionServiceIPM.getAllExamss();
        model.addAttribute("examss", examss);
        // Kiểm tra và thêm vào model

        // Thêm thông tin người dùng vào session

        return "admin-exam"; // Trả về view admin-exam.html
    }


@GetMapping("/admin")
public String admin(HttpSession session, Model model) {
    user currentUser = (user) session.getAttribute("user");

    // Kiểm tra người dùng có đăng nhập không
    if (currentUser == null) {
        model.addAttribute("loggedIn", false);
        return "redirect:/login";  // Chuyển hướng đến trang đăng nhập nếu không có session
    }

    // Kiểm tra role của người dùng
    if (!"admin".equals(currentUser.getRole())) {  // Giả sử thuộc tính 'role' lưu vai trò người dùng
        return "redirect:/";  // Chuyển hướng về trang chính nếu không phải admin
    }

    // Thêm thông tin vào model
    model.addAttribute("loggedIn", true);
    model.addAttribute("username", currentUser.getTaikhoan());

    // Lấy danh sách tất cả người dùng
    List<user> allUsers = userService.getAllUser();
    model.addAttribute("users", allUsers);

    return "admin";  // Trả về trang admin
}


//    @GetMapping("/admin-exam")
//    public String adminExam(HttpSession session, Model model) {
//        // Kiểm tra session người dùng
//        user currentUser = (user) session.getAttribute("user");
//
//        // Kiểm tra người dùng có đăng nhập không
//        if (currentUser == null) {
//            model.addAttribute("loggedIn", false);
//            return "redirect:/login";  // Chuyển hướng đến trang đăng nhập nếu không có session
//        }
//
//        // Kiểm tra role của người dùng
//        if (!"admin".equals(currentUser.getRole())) {  // Giả sử thuộc tính 'role' lưu vai trò người dùng
//            return "redirect:/";  // Chuyển hướng về trang chính nếu không phải admin
//        }
//
//        // Thêm thông tin vào model
//        model.addAttribute("loggedIn", true);
//        model.addAttribute("username", currentUser.getTaikhoan());
//
//        // Lấy tất cả bài kiểm tra
//        List<exam> examss = questionServiceIPM.getAllExamss();
//        model.addAttribute("examss", examss);
//        // Kiểm tra và thêm vào model
//
//        // Thêm thông tin người dùng vào session
//
//        return "admin-exam"; // Trả về view admin-exam.html
//    }






    @GetMapping("/contact")
    public String contact(HttpSession session, Model model) {
        user user = (user) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("loggedIn", false);
        return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
//        sessionEmail(user, model);
        model.addAttribute("user", user);
        return "contact";
    }

    public Object sessionEmail(user user, Model model) {
        if (user == null) {
//            return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
            model.addAttribute("loggedIn", false);
            return "redirect:/login";
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
        return null;
    }
}
