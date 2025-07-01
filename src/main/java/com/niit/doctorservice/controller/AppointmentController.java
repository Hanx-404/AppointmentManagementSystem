    @GetMapping("/appointments/{date}/{time}")
    public String appointments(@PathVariable LocalDate date, @PathVariable char time, HttpSession session, Model model) {
        Integer doctorId = (Integer) session.getAttribute("doctorId");
        if (doctorId == null) {
            // 处理doctorId不存在的情况，例如重定向到登录页或抛出异常
            return "redirect:/login"; // 示例处理方式
        }
        model.addAttribute("appointments", appointmentService.getAppointmentsByDoctorIdAndDateAndTime(doctorId, date, time));
        return "appointments";
    }