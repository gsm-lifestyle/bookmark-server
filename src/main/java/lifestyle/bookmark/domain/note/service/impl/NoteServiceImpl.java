package lifestyle.bookmark.domain.note.service.impl;

import lifestyle.bookmark.domain.book.domain.Book;
import lifestyle.bookmark.domain.book.domain.repository.BookRepository;
import lifestyle.bookmark.domain.book.exception.NotFoundBookException;
import lifestyle.bookmark.domain.book.exception.UnregisterBookException;
import lifestyle.bookmark.domain.member.domain.Member;
import lifestyle.bookmark.domain.member.facade.MemberFacade;
import lifestyle.bookmark.domain.note.domain.Note;
import lifestyle.bookmark.domain.note.domain.repository.NoteRepository;
import lifestyle.bookmark.domain.note.exception.NotFoundNoteException;
import lifestyle.bookmark.domain.note.presentation.dto.request.WriteNoteRequest;
import lifestyle.bookmark.domain.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final MemberFacade memberFacade;
    private final NoteRepository noteRepository;
    private final BookRepository bookRepository;


    private void verifyReadBook(Book book , Integer readPage) {
        if(book.getReadingPage() <= 0) {
            book.updateIsDoneToRead();
        } else {
            book.readBookPage(readPage);
        }
    }

    private void verifyMember(Member member) {
        Member currentMember = memberFacade.getCurrentMember();
        if(!member.equals(currentMember))
            throw new UnregisterBookException("멤버가 일치하지 않습니다.");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void writeNote(WriteNoteRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundBookException("존재하지 않은 책입니다."));
        Integer readPage = request.getReadPage();

       verifyMember(book.getMember());

        if(!book.isDoneToRead())
            verifyReadBook(book, readPage);

        bookRepository.save(book);

        Note note = Note.builder()
                .noteContent(request.getNoteContent())
                .noteTitle(request.getNoteTitle())
                .member(book.getMember())
                .book(book)
                .build();

        noteRepository.save(note);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNote(Integer noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NotFoundNoteException("기록장을 찾을 수 없습니다."));

        verifyMember(note.getMember());

        noteRepository.deleteById(noteId);
    }


}
