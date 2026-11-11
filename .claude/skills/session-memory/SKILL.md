---
name: session-memory
description: Persist and restore work progress in memory.md so the next chat can continue. Use this skill at the start of every conversation and after every completed work round. Trigger whenever the user asks to continue, resume, remember progress, wrap up a session, or after finishing implementation, setup, tests, or docs in this repo — even if they do not mention memory.md.
---

# Session memory

File duy nhất: `memory.md` ở root repo (`d:\TanTLN_RAG\demo_selenium\memory.md`).

Đây là nhật ký tiến độ giữa các lượt chat. Không nhầm với CLAUDE.md (kế hoạch/mục tiêu) hay với memory system nội bộ của Claude.

## Khi bắt đầu lượt chat

1. Đọc `memory.md` trước khi làm việc khác.
2. Nếu file trống hoặc chưa có mục `## Current`: coi như chưa có tiến độ, bắt đầu từ bước 1 trong CLAUDE.md.
3. Tiếp tục từ `Next` — đừng làm lại việc đã `Done`.
4. Nếu `memory.md` mâu thuẫn với file thật trên đĩa, tin file trên đĩa và sửa `memory.md`.

## Khi kết thúc một lượt làm việc

Ghi **ngay** vào `memory.md` trước khi trả lời xong, mỗi khi lượt đó đã:

- tạo/sửa file
- cài môi trường
- chạy test
- quyết định kỹ thuật
- hoặc user bảo dừng / continue sau

Không đợi user nhắc. Lượt chỉ hỏi (không thay đổi repo) thì không bắt buộc ghi, trừ khi có quyết định cần nhớ.

Ghi đè toàn bộ file theo template dưới — không append vô hạn. Giữ tối đa 1 mục `Last session` (lượt vừa rồi) và 3–5 dòng `History` (các lượt cũ hơn, mỗi dòng một lượt).

## Template `memory.md`

```markdown
# Session memory — demo_selenium

## Current
- **Date:** YYYY-MM-DD
- **Phase:** 1 TestNG | 2 Selenium env | 3 Finding Web Elements | done
- **Status:** in-progress | blocked | done

## Done
- việc đã xong (checklist thật, không đoán)

## In progress
- việc đang dở (file, lệnh, lỗi nếu có)

## Next
- bước tiếp theo cụ thể (1–3 ý, làm được ngay)

## Blockers
- trống nếu không có; nếu có thì gồm lệnh/lỗi ngắn

## Last session
- YYYY-MM-DD: tóm tắt 1–3 câu những gì lượt này làm

## History
- YYYY-MM-DD: tóm tắt 1 câu
```

## Quy tắc

- Ngày dùng lịch tuyệt đối (`2026-11-10`), không viết "hôm nay".
- `Done` / `Next` bám checklist trong CLAUDE.md.
- Không paste log dài; lỗi thì 1 dòng lệnh + 1 dòng message.
- Không ghi secret, password, path ChromeDriver tạm.
- Sau khi ghi, câu trả lời cho user không cần nhắc lại toàn bộ `memory.md`.
