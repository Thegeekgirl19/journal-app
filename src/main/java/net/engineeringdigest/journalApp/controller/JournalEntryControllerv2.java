package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2
{
	@Autowired
	JournalEntryService journalEntryService;

	@Autowired
	UserService userService;

	@GetMapping()
	public ResponseEntity<List<JournalEntry>> geAllJournalEntriesOfUser()
	{
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUserName(userName);
		List<JournalEntry> all = user.getJournalEntries();
		if (all != null && !all.isEmpty())
		{
			return new ResponseEntity<>(all, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry)
	{
		try
		{
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			//			saveJournal will handle the user and journal both
			journalEntryService.saveJournal(entry, userName);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/id/{myId}")
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId)
	{
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User byUserName = userService.findByUserName(name);
		List<JournalEntry> collect = byUserName.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
		if (!collect.isEmpty())
		{
			Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
			if (journalEntry.isPresent())
			{
				return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{myId}")
	//	? signifies wild card i.e., method can return RequestEntity of any type
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId)
	{
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		if (journalEntryService.deleteById(myId, name))
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{myId}")
	public ResponseEntity<?> updateUser(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry)
	{
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		List<JournalEntry> collect = userService.findByUserName(name).getJournalEntries().stream().filter(x -> x.getId().equals(myId))
				.collect(Collectors.toList());

		if (!collect.isEmpty())
		{
			Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
			if (journalEntry.isPresent())
			{
				JournalEntry old = journalEntry.get();
				old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equalsIgnoreCase("") ? newEntry.getTitle() : old.getTitle());
				old.setContent(newEntry.getContent() != null && !newEntry.getContent().equalsIgnoreCase("") ? newEntry.getContent() : old.getContent());
				journalEntryService.saveJournal(old);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
