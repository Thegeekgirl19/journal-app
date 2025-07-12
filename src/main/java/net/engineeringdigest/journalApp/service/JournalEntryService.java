package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class JournalEntryService
{

	@Autowired
	JournalEntryRepository journalEntryRepository;

	@Autowired
	UserService userService;

	@Transactional
	public JournalEntry saveJournal(JournalEntry entry, String userName)
	{
		try
		{
			User user = userService.findByUserName(userName);
			entry.setDate(LocalDateTime.now());
			JournalEntry saved = journalEntryRepository.save(entry);
			user.getJournalEntries().add(saved);
			userService.saveUser(user);
			return saved;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new RuntimeException("Error in saving journal: " + e);
		}
	}

	public Optional<JournalEntry> findById(ObjectId id)
	{
		return journalEntryRepository.findById(id);
	}

	@Transactional
	public boolean deleteById(ObjectId id, String userName)
	{
		try
		{
			User user = userService.findByUserName(userName);
			boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
			if (removed)
			{
				userService.saveUser(user);
				journalEntryRepository.deleteById(id);
			}
			return removed;
		}
		catch (Exception e)
		{
			throw new RuntimeException("An error has occured while deleting the entry: " + e);
		}

	}

	public void saveJournal(JournalEntry entry)
	{
		entry.setDate(LocalDateTime.now());
		journalEntryRepository.save(entry);
	}
}
