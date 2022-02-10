package hello.toy_project.service;

import hello.toy_project.entity.Board;
import hello.toy_project.entity.User;
import hello.toy_project.repository.BoardRepository;
import hello.toy_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Board save(String username, Board board) {
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
